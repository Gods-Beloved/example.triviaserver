package com.example.domain.model.advertisement

@kotlinx.serialization.Serializable
data class AdResponse(
    val success:Boolean,
    val ads:List<Ads>? = null
)
