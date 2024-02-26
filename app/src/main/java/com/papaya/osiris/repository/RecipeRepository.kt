package com.papaya.osiris.repository

import com.papaya.osiris.data.Recipe
import com.papaya.osiris.data.RecipeRequest
import com.papaya.osiris.services.RetrofitInstance
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import java.io.File

class RecipeRepository {
    private val service = RetrofitInstance.recipeService

    suspend fun list(): Call<List<Recipe>> {
        return service.list()
    }

    suspend fun get(id: String): Call<Recipe> {
        return service.get(id)
    }

    suspend fun create(token: String, recipe: RecipeRequest): Call<Recipe> {
        return service.create(token, recipe)
    }

    suspend fun update(token: String, id: String, recipe: RecipeRequest): Call<Recipe> {
        return service.update(token, id, recipe)
    }

    suspend fun updateImage(token: String, id: String, image: File): Call<Unit> {
        val imagem = MultipartBody.Part.createFormData(
            "imagem",
            image.name,
            image.asRequestBody()
        )
        return service.updateImage(token, id, imagem)
    }

    suspend fun delete(token: String, id: String): Call<Unit> {
        return service.delete(token, id)
    }
}