package com.papaya.osiris.repository

import com.papaya.osiris.data.Panc
import com.papaya.osiris.services.RetrofitInstance
import retrofit2.Call

class PancRepository {
    private val service = RetrofitInstance.pancService

    suspend fun list(): Call<List<Panc>> {
        return service.list()
    }

    suspend fun get(id: String): Call<Panc> {
        return service.get(id)
    }
}