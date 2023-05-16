package com.example.domain.model.response

import com.example.domain.model.user.account_info.Account

@kotlinx.serialization.Serializable

data class TransactionResponse(
    val success:Boolean,
    val account: List<Account>? = null,
)
