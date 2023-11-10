package com.papaya.osiris.data

data class Recipe(
    val id: String,
    val nome: String,
    val pancs: List<Panc>,
    val ingredientes: List<String>,
    val preparo: List<String>,
    val imagem: String,
    val usuarioId: String,
)