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