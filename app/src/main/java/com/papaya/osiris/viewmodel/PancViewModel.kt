package com.papaya.osiris.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaya.osiris.data.Panc
import com.papaya.osiris.repository.PancRepository
import kotlinx.coroutines.launch

class PancViewModel: ViewModel() {
    private val repository = PancRepository()

    private val _pancs = MutableLiveData<List<Panc>?>(null)
    val pancs: LiveData<List<Panc>?> = _pancs

    fun fetch(success: suspend (List<Panc>) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                _pancs.value = repository.list().execute().body()
                _pancs.value?.let { success(it) }
            } catch (e: RuntimeException) {
                Log.e("fetch pancs error", e.message ?: "unknown error")
                failure()
            }
        }
    }

    fun get(id: String, success: suspend (Panc) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val pancResponse = repository.get(id).execute().body()
                if (pancResponse != null) success(pancResponse) else {
                    pancs.let { pancList ->
                        pancList.value?.find { it.id == id }?.let { success(it) } ?: failure()
                    }
                }
            } catch (e: RuntimeException) {
                Log.e("get panc error", e.message ?: "unknown error")
                failure()
            }
        }
    }
}