package com.example.database.tables

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val email = varchar("email", 150).uniqueIndex()
    val mobile = varchar("mobile", 100)
    val password = varchar("password", 100)

    override val primaryKey = PrimaryKey(id)
}