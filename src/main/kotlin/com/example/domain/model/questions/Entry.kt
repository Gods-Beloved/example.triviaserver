package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class Entry(
    val sportsPrice:Int,
    val academicPrice:Int,
    val entertainmentPrice:Int,
    val generalPrice:Int,
    val sportsWinnings:Int,
    val academicWinnings:Int,
    val entertainmentWinnings:Int,
    val generalWinnings:Int
)
