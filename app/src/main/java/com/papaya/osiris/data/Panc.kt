package com.papaya.osiris.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Panc(
    val id: String,
    val nome: String,
    val beneficios: String,
    val imagem: String,
    val descricao: String,
    val cultivo: @RawValue List<String>,
): Parcelable