package com.leightondarkins.service

import java.util.*

enum class Type { INCOME, EXPENSE }

class TransactionType(val id: UUID, val type: Type)