package com.papaya.osiris.services

import com.google.gson.GsonBuilder
import io.github.cdimascio.dotenv.dotenv
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val dotenv = dotenv {
        directory="/assets"
        filename="env"
    }
    private val BASE_URL = dotenv["BASE_URL"]

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