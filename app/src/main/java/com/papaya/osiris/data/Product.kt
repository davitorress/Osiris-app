package com.papaya.osiris.data

data class Product(
    val id: String,
    val name: String,
    val image: String,
    val onClick: () -> Unit
)