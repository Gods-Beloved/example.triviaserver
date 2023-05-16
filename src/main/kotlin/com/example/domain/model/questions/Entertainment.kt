package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class Entertainment(
    val question: String,
    val options:List<String>,
    val answer:Int,
    val received:Boolean = false
)
