package com.papaya.osiris.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaya.osiris.data.Recipe
import com.papaya.osiris.data.RecipeRequest
import com.papaya.osiris.repository.RecipeRepository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.File

class RecipeViewModel: ViewModel() {
    private val repository = RecipeRepository()

    private val _recipes = MutableLiveData<List<Recipe>?>(null)
    val recipes: LiveData<List<Recipe>?> = _recipes

    fun fetch(success: suspend (List<Recipe>) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                _recipes.value = repository.list().execute().body()
                _recipes.value?.let { success(it) }
            } catch (e: RuntimeException) {
                Log.e("fetch recipes error", e.message ?: "unknown error")
                failure()
            }
        }
    }

    fun get(id: String, success: suspend (Recipe) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val recipeResponse = repository.get(id).execute().body()
                if (recipeResponse != null) success(recipeResponse) else {
                    recipes.let { recipeList ->
                        recipeList.value?.find { it.id == id }?.let { success(it) } ?: failure()
                    }
                }
            } catch (e: RuntimeException) {
                Log.e("get recipe error", e.message ?: "unknown error")
                failure()
            }
        }
    }

    fun create(token: String, recipe: RecipeRequest, success: suspend (Recipe) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val recipeResponse = repository.create(token, recipe).execute().body()
                if (recipeResponse != null) success(recipeResponse) else failure()
            } catch (e: RuntimeException) {
                Log.e("create recipe error", e.message ?: "unknown error")
                failure()
            }
        }
    }

    fun update(token: String, id: String, recipe: RecipeRequest, success: suspend (Recipe) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val recipeResponse = repository.update(token, id, recipe).execute().body()
                if (recipeResponse != null) success(recipeResponse) else failure()
            } catch (e: RuntimeException) {
                Log.e("update recipe error", e.message ?: "unknown error")
                failure()
            }
        }
    }

    fun updateImage(token: String, id: String, image: File, success: suspend () -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val response = repository.updateImage(token, id, image).awaitResponse()
                if (response.isSuccessful) success() else failure()
            } catch (e: RuntimeException) {
                Log.e("update image error", e.message ?: "unknown error")
                failure()
            }
        }
    }

    fun delete(token: String, id: String, success: suspend () -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val response = repository.delete(token, id).awaitResponse()
                if (response.isSuccessful) success() else failure()
            } catch (e: RuntimeException) {
                Log.e("delete recipe error", e.message ?: "unknown error")
                failure()
            }
        }
    }
}