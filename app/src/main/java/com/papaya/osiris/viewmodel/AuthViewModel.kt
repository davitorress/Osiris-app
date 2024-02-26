package com.papaya.osiris.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaya.osiris.repository.LoginRepository
import com.papaya.osiris.utils.decryptToken
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val loginRepository = LoginRepository()

    private val _token = MutableLiveData<String?>(null)
    val token: LiveData<String?> = _token

    val userId: String?
        get() = token.value?.let { decryptToken(it) }

    fun login(email: String, password: String, success: suspend (String) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                _token.value = loginRepository.login(email, password).execute().body()?.token
                _token.value?.let { success(it) }
            } catch (e: RuntimeException) {
                Log.e("login error", e.message ?: "unknown error")
                failure()
            }
        }
    }
}
