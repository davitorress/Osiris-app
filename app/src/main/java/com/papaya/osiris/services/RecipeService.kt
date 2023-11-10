package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.Recipe
import com.papaya.osiris.data.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RecipeService {
    @GET("receitas")
    fun list(@Header("Authorization") token: String): Call<List<Recipe>>
}

class RecipeWebClient {
    fun list(token: String, success: (List<Recipe>) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().recipeService().list(token)
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
}