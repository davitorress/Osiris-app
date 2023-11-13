package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.RetrofitInitializer
import com.papaya.osiris.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @GET("usuarios/{id}")
    fun get(@Header("Authorization") token: String,  @Path("id") id: String): Call<User>
}

class UserWebClient {
    fun get(token: String, id: String, success: (User) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().userService().get(token, id)
        call.enqueue(object: Callback<User?> {
            override fun onResponse(call: Call<User?>?, response: Response<User?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<User?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }
}