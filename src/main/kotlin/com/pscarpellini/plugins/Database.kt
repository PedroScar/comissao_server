package com.pscarpellini.plugins

import com.pscarpellini.AmbientController
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {

    if (AmbientController.isDevelopment)
        Database.connect(
            "jdbc:postgresql://localhost:5432/decor_bonus_db",
            user = "postgres",
            password = "Monib@009"
        )
    else
        Database.connect(
            "jdbc:postgresql://ep-blue-unit-a5d79dcb.us-east-2.aws.neon.tech:5432/comissaodb",
            user = "comissaodb_owner",
            password = "T3JMNw0IYkBK"
        )
}