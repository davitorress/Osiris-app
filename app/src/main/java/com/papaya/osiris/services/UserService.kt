package com.papaya.osiris.services

import com.papaya.osiris.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @GET("usuarios/{id}")
    suspend fun get(@Header("Authorization") token: String,  @Path("id") id: String): Call<User>
}
