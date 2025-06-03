package com.pscarpellini.plugins

import com.pscarpellini.AmbientController
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {
    if (AmbientController.isDevelopment)
        Database.connect(
            "jdbc:postgresql://dpg-ctomo7popnds73fj42qg-a.oregon-postgres.render.com:5432/lumenappsdb",
            user = "lumenappsdb_user",
            password = "eNAb9LCF95F2JTUGDUby7xrWEIUWin0q"
        )
    else
        Database.connect(
            "jdbc:postgresql://dpg-ctomo7popnds73fj42qg-a/lumenappsdb",
            user = "lumenappsdb_user",
            password = "eNAb9LCF95F2JTUGDUby7xrWEIUWin0q"
        )
}