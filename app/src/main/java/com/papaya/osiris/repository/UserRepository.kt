package com.papaya.osiris.repository

import com.papaya.osiris.data.User
import com.papaya.osiris.services.RetrofitInstance
import retrofit2.Call

class UserRepository {
    private val service = RetrofitInstance.userService

    suspend fun get(token: String, id: String): Call<User> {
        return service.get(token, id)
    }
}