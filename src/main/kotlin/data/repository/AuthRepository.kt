package com.example.data.repository

import com.example.data.model.UserData
import com.example.database.tables.UsersTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class AuthRepository {
    fun login(email: String, password: String): UserData? {
        val resultRow = transaction {
            UsersTable.selectAll().where { (UsersTable.email eq email) }
                .singleOrNull()
        }

        return if (resultRow != null && BCrypt.checkpw(password, resultRow[UsersTable.password])) {
            UserData(
                id = resultRow[UsersTable.id].toString(),
                name = resultRow[UsersTable.name],
                email = resultRow[UsersTable.email],
                mobile = resultRow[UsersTable.mobile]
            )
        } else {
            null
        }
    }
}