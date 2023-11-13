package com.papaya.osiris.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Recipe(
    val id: String,
    val nome: String,
    val descricao: String,
    val pancs: @RawValue List<String>,
    val ingredientes: @RawValue List<String>,
    val preparo: @RawValue List<String>,
    val imagem: String,
    val usuarioId: String,
): Parcelable