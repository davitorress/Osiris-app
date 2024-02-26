package com.papaya.osiris.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val nome: String,
    val email: String,
    val pancsFavoritasId: List<String>,
    val receitasSalvasId: List<String>,
    val perfil: String,
    val assinatura: Assinatura,
    val imagem: String,
): Parcelable