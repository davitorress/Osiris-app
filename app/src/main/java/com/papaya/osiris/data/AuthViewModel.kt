package com.papaya.osiris.data

import androidx.lifecycle.ViewModel
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel: ViewModel() {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    val userId: String
        get() {
            val tokenValue = token.value
            val key = Keys.hmacShaKeyFor("b2C4y9t8v7r6u5i4o3p2q1a0z9x8w7v6u5i4o3p2q1a0".toByteArray())
            val claims = Jwts.parser().verifyWith(key)
                .build().parseSignedClaims(tokenValue).payload
            return claims["id"] as? String ?: ""
        }

    fun saveToken(token: String) {
        _token.value = token
    }

    fun clearToken() {
        _token.value = null
    }
}
