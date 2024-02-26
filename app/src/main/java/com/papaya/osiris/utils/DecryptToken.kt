package com.papaya.osiris.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys

fun decryptToken(token: String): String {
    val SECRET_KEY = "b2C4y9t8v7r6u5i4o3p2q1a0z9x8w7v6u5i4o3p2q1a0".toByteArray()

    val key = Keys.hmacShaKeyFor(SECRET_KEY)
    val claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).payload
    return claims["id"] as? String ?: ""
}