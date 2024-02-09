package com.jecky.jetpackcoposefirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.model.User
import com.jecky.jetpackcoposefirebase.util.AppConstants
import kotlinx.coroutines.tasks.await

class UserRepository {
    var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun addUserData(user: User): APIResult<User> {
        return try {
            val userResponse =
                fireStore.collection(AppConstants.TABLE_USER).add(user).await()
            APIResult.APISuccess(data = user.apply { userId = userResponse.id })
        } catch (e: Exception) {
            APIResult.APIFailure(errorMessage = e.message)
        }
    }

    suspend fun addFavoriteQuote(quoteId: String?): APIResult<Boolean>{
return try {
    val response = fireStore.collection(AppConstants.TABLE_USER).document(quoteId!!)//TODO add logic for add favorite quote
    APIResult.APISuccess(data = null)
}
catch (e: Exception){
    APIResult.APIFailure(errorMessage = "Failed")
}
    }
}