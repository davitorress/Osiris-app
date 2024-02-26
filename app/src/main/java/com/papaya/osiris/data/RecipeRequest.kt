package com.papaya.osiris.data

data class RecipeRequest(
    val nome: String,
    val descricao: String,
    val pancs: List<String>,
    val ingredientes: List<String>,
    val preparo: List<String>,
    val imagem: String?,
    val usuarioId: String,
)