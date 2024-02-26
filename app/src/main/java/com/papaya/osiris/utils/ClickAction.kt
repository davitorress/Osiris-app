package com.papaya.osiris.utils

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

interface ClickAction : Parcelable {
    fun onClick()
}

@Parcelize
class ClickActionImpl(private val onClickFunction: () -> Unit) : ClickAction {
    override fun onClick() {
        onClickFunction.invoke()
    }

    override fun describeContents(): Int = 0

    companion object : Parceler<ClickActionImpl> {
        override fun ClickActionImpl.write(parcel: Parcel, flags: Int) {}

        override fun create(parcel: Parcel): ClickActionImpl {
            return ClickActionImpl {}
        }
    }
}