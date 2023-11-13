package com.papaya.osiris.data

import com.google.gson.GsonBuilder
import com.papaya.osiris.services.LoginService
import com.papaya.osiris.services.PancService
import com.papaya.osiris.services.RecipeService
import com.papaya.osiris.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.67:8080/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    fun loginService() = retrofit.create(LoginService::class.java)
    fun pancService() = retrofit.create(PancService::class.java)
    fun recipeService() = retrofit.create(RecipeService::class.java)
    fun userService() = retrofit.create(UserService::class.java)
}