package com.papaya.osiris.services

import com.papaya.osiris.data.Login
import com.papaya.osiris.data.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun login(@Body login: Login): Call<TokenResponse>
}