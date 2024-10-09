package com.pscarpellini

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.*

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

inline fun <reified T> T.logeer(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

//fun log(message: String) {
//    private val logger = myLogger()
//
//    fun main() {
//        logger.info("Hello World")
//    }
//}

inline fun <reified T> T.myLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}