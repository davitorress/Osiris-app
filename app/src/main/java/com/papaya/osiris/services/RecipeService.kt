package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.Recipe
import com.papaya.osiris.data.RecipeRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface RecipeService {
    @GET("receitas")
    suspend fun list(): Call<List<Recipe>>

    @GET("receitas/{id}")
    suspend fun get(@Path("id") id: String): Call<Recipe>

    @POST("receitas")
    suspend fun create(@Header("Authorization") token: String, @Body recipe: RecipeRequest): Call<Recipe>

    @PUT("receitas/{id}")
    suspend fun update(@Header("Authorization") token: String, @Path("id") id: String, @Body recipe: RecipeRequest): Call<Recipe>

    @Multipart
    @PATCH("receitas/{id}/imagem")
    suspend fun updateImage(@Header("Authorization") token: String, @Path("id") id: String, @Part imagem: MultipartBody.Part): Call<Unit>

    @DELETE("receitas/{id}")
    suspend fun delete(@Header("Authorization") token: String, @Path("id") id: String): Call<Unit>
}
