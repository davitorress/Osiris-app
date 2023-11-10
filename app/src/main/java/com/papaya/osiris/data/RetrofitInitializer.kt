package com.papaya.osiris.data

import com.papaya.osiris.services.LoginService
import com.papaya.osiris.services.PancService
import com.papaya.osiris.services.RecipeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.233:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun loginService() = retrofit.create(LoginService::class.java)
    fun pancService() = retrofit.create(PancService::class.java)
    fun recipeService() = retrofit.create(RecipeService::class.java)
}