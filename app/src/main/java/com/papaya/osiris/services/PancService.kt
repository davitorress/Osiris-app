package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.Panc
import com.papaya.osiris.data.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PancService {
    @GET("pancs")
    fun list(): Call<List<Panc>>

    @GET("pancs/{id}")
    fun get(@Path("id") id: String): Call<Panc>
}

class PancWebClient {
    fun list(success: (List<Panc>) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().pancService().list()
        call.enqueue(object: Callback<List<Panc>?> {
            override fun onResponse(call: Call<List<Panc>?>?, response: Response<List<Panc>?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<List<Panc>?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }

    fun get(id: String, success: (Panc) -> Unit, failure: () -> Unit = {}) {
        val call = RetrofitInitializer().pancService().get(id)
        call.enqueue(object: Callback<Panc?> {
            override fun onResponse(call: Call<Panc?>?, response: Response<Panc?>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { success(it) }
                } else {
                    failure()
                    response?.errorBody()?.let {
                        Log.e("onResponse error", it.string())
                    }
                }
            }

            override fun onFailure(call: Call<Panc?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message ?: "unknown error")
            }
        })
    }
}
