package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.AuthViewModel
import com.papaya.osiris.data.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class Login(
    val email: String,
    val senha: String
)

data class TokenResponse(
    val token: String
)

interface LoginService {
    @POST("login")
    fun login(@Body login: Login): Call<TokenResponse>
}

class LoginWebClient {
    fun login(login: Login, authViewModel: AuthViewModel, success: (String) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().loginService().login(login)
        call.enqueue(object: Callback<TokenResponse?> {
            override fun onResponse(call: Call<TokenResponse?>?, response: Response<TokenResponse?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let {
                        success(it.token)
                        authViewModel.saveToken(it.token)
                    }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<TokenResponse?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }
}