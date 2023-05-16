package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class FeeSizeResponse(
    val success:Boolean,
    val entryFee:Int? = null,
    val winningFee:Int? = null,
    val size:Int?=null,
)
