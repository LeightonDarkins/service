package com.leightondarkins.service

import org.apache.logging.log4j.LogManager
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TransactionController {
    private val logger = LogManager.getLogger("GreetingController")

    @PostMapping("/transaction")
    fun createTransaction(@RequestBody body: Transaction) {
        logger.info("POST - Creating Transaction")

        transaction {
            addLogger(StdOutSqlLogger)

            TransactionDAO.new {
                amount = body.amount
                type = body.type.toString()
            }
        }
    }

    @GetMapping("/transaction")
    fun getTransactions(): List<Transaction> {
        logger.info("GET - Getting Transactions")

        val transactionDAOs = transaction {
            addLogger(StdOutSqlLogger)

            TransactionDAO.all().toList()
        }

        return transactionDAOs.map { t -> Transaction(t.id.value, t.amount, Type.valueOf(t.type)) }
    }

    @GetMapping("/transaction/{id}")
    fun getTransaction(@PathVariable("id") id: UUID): Transaction {
        logger.info("GET - Getting Transaction $id")

        val result = transaction {
            addLogger(StdOutSqlLogger)

            TransactionDAO[id]
        }

        return Transaction(result.id.value, result.amount, Type.valueOf(result.type))
    }
}

object Transactions: UUIDTable() {
    val amount: Column<Long> = long("amount")
    val type: Column<String> = varchar("type", 50)
}

class TransactionDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TransactionDAO>(Transactions)

    var amount by Transactions.amount
    var type by Transactions.type
}