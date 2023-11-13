package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.Recipe
import com.papaya.osiris.data.RetrofitInitializer
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

data class RecipeRequest(
    val nome: String,
    val descricao: String,
    val pancs: List<String>,
    val ingredientes: List<String>,
    val preparo: List<String>,
    val imagem: String?,
    val usuarioId: String,
)

data class RecipeImageRequest(
    @Part val imagem: String,
)

interface RecipeService {
    @GET("receitas")
    fun list(): Call<List<Recipe>>

    @GET("receitas/{id}")
    fun get(@Path("id") id: String): Call<Recipe>

    @POST("receitas")
    fun create(@Header("Authorization") token: String, @Body recipe: RecipeRequest): Call<Recipe>

    @PUT("receitas/{id}")
    fun update(@Header("Authorization") token: String, @Path("id") id: String, @Body recipe: RecipeRequest): Call<Recipe>

    @Multipart
    @PATCH("receitas/{id}/imagem")
    fun updateImage(@Header("Authorization") token: String, @Path("id") id: String, @Part imagem: MultipartBody.Part): Call<Unit>

    @DELETE("receitas/{id}")
    fun delete(@Header("Authorization") token: String, @Path("id") id: String): Call<Unit>
}

class RecipeWebClient {
    fun list(success: (List<Recipe>) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().list()
        call.enqueue(object: Callback<List<Recipe>?> {
            override fun onResponse(call: Call<List<Recipe>?>?, response: Response<List<Recipe>?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<List<Recipe>?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }

    fun get(id: String, success: (Recipe) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().get(id)
        call.enqueue(object: Callback<Recipe?> {
            override fun onResponse(call: Call<Recipe?>?, response: Response<Recipe?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<Recipe?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }

    fun create(token: String, recipe: RecipeRequest, success: (Recipe) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().create(token, recipe)
        call.enqueue(object: Callback<Recipe?> {
            override fun onResponse(call: Call<Recipe?>?, response: Response<Recipe?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<Recipe?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }

    fun update(token: String, id: String, recipe: RecipeRequest, success: (Recipe) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().update(token, id, recipe)
        call.enqueue(object: Callback<Recipe?> {
            override fun onResponse(call: Call<Recipe?>?, response: Response<Recipe?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<Recipe?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }

    fun updateImage(token: String, id: String, imagem: MultipartBody.Part, success: () -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().updateImage(token, id, imagem)
        call.enqueue(object: Callback<Unit?> {
            override fun onResponse(call: Call<Unit?>?, response: Response<Unit?>?) {
                if (response?.isSuccessful == true) {
                    success()
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<Unit?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }

    fun delete(token: String, id: String, success: () -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().delete(token, id)
        call.enqueue(object: Callback<Unit?> {
            override fun onResponse(call: Call<Unit?>?, response: Response<Unit?>?) {
                if (response?.isSuccessful == true) {
                    success()
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<Unit?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }
}