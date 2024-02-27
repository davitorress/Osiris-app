package com.papaya.osiris.data

import android.os.Parcelable
import com.papaya.osiris.utils.ClickAction
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val image: String,
    val description: String? = null,
    val isFavorite: Boolean = false,
    val clickAction: @RawValue ClickAction
) : Parcelable
