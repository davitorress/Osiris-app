package com.papaya.osiris.data

import com.google.gson.GsonBuilder
import com.papaya.osiris.services.LoginService
import com.papaya.osiris.services.PancService
import com.papaya.osiris.services.RecipeService
import com.papaya.osiris.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.1.67:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    val loginService: LoginService by lazy { retrofit.create(LoginService::class.java) }
    val pancService: PancService by lazy { retrofit.create(PancService::class.java) }
    val recipeService: RecipeService by lazy { retrofit.create(RecipeService::class.java) }
    val userService: UserService by lazy { retrofit.create(UserService::class.java) }
}