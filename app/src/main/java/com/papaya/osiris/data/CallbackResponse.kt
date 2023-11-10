package com.papaya.osiris.data

interface CallbackResponse<T> {
    fun success(response: T)
    fun failure()
}