package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class Live(
    val entry:Entry,
    val entertainment: List<Entertainment>,
    val sports:List<Sports>

)
