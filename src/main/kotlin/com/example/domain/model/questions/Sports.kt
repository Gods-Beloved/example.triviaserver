package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class Sports(
    val question: String? = null,
    val options:List<String>? = null,
    val answer:Int? = null,
    val received:Boolean = false
)
