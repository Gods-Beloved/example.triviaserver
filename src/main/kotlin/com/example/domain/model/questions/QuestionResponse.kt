package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class QuestionResponse(
    val success:Boolean,
    val sports: Sports? = null,
    val entertainment: Entertainment? = null,
    val general: General? = null,
    val academic: Academic? = null
)