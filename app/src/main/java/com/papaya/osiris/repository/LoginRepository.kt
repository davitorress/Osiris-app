package com.papaya.osiris.repository

import com.papaya.osiris.data.Login
import com.papaya.osiris.data.TokenResponse
import com.papaya.osiris.services.RetrofitInstance
import retrofit2.Call

class LoginRepository {
    private val service = RetrofitInstance.loginService

    suspend fun login(email: String, senha: String): Call<TokenResponse> {
        return service.login(Login(email, senha))
    }
}