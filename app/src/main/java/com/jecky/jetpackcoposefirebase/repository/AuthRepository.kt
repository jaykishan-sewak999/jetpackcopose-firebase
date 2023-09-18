package com.jecky.jetpackcoposefirebase.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jecky.jetpackcoposefirebase.network.APIResult
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private var auth = Firebase.auth

    suspend fun register(email: String, password: String): APIResult<AuthResult>?{

        var user: FirebaseUser? = null
       return try {
           val result =  auth.createUserWithEmailAndPassword(email, password).await()
             APIResult.APISuccess(result)
        }
        catch (_: Exception){
            APIResult.APIFailure(errorMessage = "")
        }

    }

}