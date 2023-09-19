package com.jecky.jetpackcoposefirebase.model

import com.google.firebase.auth.AuthResult

data class UsersAuthResponse(
    val authResult: AuthResult? = null,
    var message: String? = null,
    var status: String? = null
)
enum class APIRESULT {
    FAIL,
    SUCCESS
}