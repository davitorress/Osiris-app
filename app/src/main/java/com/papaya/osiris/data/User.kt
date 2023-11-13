package com.papaya.osiris.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Assinatura(
    val dataInicio: LocalDate,
    val dataTermino: LocalDate,
    val ativa: Boolean,
): Parcelable

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