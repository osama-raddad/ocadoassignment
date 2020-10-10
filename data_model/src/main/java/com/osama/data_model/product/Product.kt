package com.osama.data_model.product


data class Product(
	val id: Int,
	val price: Double,
	val title: String,
	val imageUrl: String,
	val description: String,
	val allergyInformation: String
)