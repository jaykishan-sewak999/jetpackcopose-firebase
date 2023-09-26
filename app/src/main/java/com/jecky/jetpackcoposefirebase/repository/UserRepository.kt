package com.jecky.jetpackcoposefirebase.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun addUserData(user: User): APIResult<User> {

        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            APIResult.APISuccess(data = result)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }
}