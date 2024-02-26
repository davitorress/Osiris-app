package com.papaya.osiris.services

import com.papaya.osiris.data.Panc
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PancService {
    @GET("pancs")
    suspend fun list(): Call<List<Panc>>

    @GET("pancs/{id}")
    suspend fun get(@Path("id") id: String): Call<Panc>
}
