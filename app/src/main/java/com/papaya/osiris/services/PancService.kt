package com.papaya.osiris.services

import android.util.Log
import com.papaya.osiris.data.Panc
import com.papaya.osiris.data.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

interface PancService {
    @GET("pancs")
    fun list(): Call<List<Panc>>
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
}
