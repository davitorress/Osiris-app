package com.papaya.osiris.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaya.osiris.data.User
import com.papaya.osiris.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val repository = UserRepository()

    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> = _user

    fun fetch(token: String, id: String, success: suspend (User) -> Unit, failure: suspend () -> Unit = {}) {
        viewModelScope.launch {
            try {
                _user.value = repository.get(token, id).execute().body()
                _user.value?.let { success(it) }
            } catch (e: RuntimeException) {
                Log.e("fetch user error", e.message ?: "unknown error")
                failure()
            }
        }
    }
}