package com.example.data.repository

import com.example.data.model.UserData
import com.example.database.tables.UsersTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {

    fun insertUser(name: String, email: String, password: String, mobile: String): UserData? {
        val userData = getUserByEmail(email)
        if (userData != null) {
            return null
        }

        val id = transaction {
            UsersTable.insert {
                it[UsersTable.name] = name
                it[UsersTable.email] = email
                it[UsersTable.password] = password
                it[UsersTable.mobile] = mobile
            } get UsersTable.id
        }

        return UserData(id.toString(), name, email, mobile)
    }

    fun getUserByEmail(email: String): UserData? {
        return transaction {
            UsersTable.selectAll().where { UsersTable.email eq email }
                .map {
                    UserData(
                        id = it[UsersTable.id].toString(),
                        name = it[UsersTable.name],
                        email = it[UsersTable.email],
                        mobile = it[UsersTable.mobile]
                    )
                }
                .singleOrNull()
        }
    }
}