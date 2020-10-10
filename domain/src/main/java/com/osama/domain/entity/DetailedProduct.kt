package com.osama.domain.entity

data class DetailedProduct(
    val price: Double,
    val title: String,
    val imageUrl: String,
    val description: String,
    val allergyInformation: String
)