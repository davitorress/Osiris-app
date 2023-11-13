package com.papaya.osiris.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

interface ClickAction : Parcelable {
    fun onClick()
}

@Parcelize
class ClickActionImpl(private val onClickFunction: () -> Unit) : ClickAction {
    override fun onClick() {
        onClickFunction.invoke()
    }

    // Métodos necessários para a interface Parcelable
    override fun describeContents(): Int = 0

    companion object : Parceler<ClickActionImpl> {
        override fun ClickActionImpl.write(parcel: Parcel, flags: Int) {
            // Como não temos dados adicionais para escrever, não fazemos nada aqui.
        }

        override fun create(parcel: Parcel): ClickActionImpl {
            // Como não temos dados adicionais, retornamos uma nova instância diretamente.
            return ClickActionImpl {}
        }
    }
}


@Parcelize
data class Product(
    val id: String,
    val name: String,
    val image: String,
    val clickAction: @RawValue ClickAction
) : Parcelable
