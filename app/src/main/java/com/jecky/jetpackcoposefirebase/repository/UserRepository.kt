package com.jecky.jetpackcoposefirebase.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
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

    suspend fun addFavoriteQuote(
        favQuotes: ArrayList<String>
    ): APIResult<Boolean> {
        return try {
            val response = fireStore.collection(AppConstants.TABLE_USER)
                .document("2o9O8atZO0nHJL48OLEf")
                .update("favorites", FieldValue.arrayUnion(favQuotes[0])).addOnSuccessListener {
                    Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener {
                    e -> Log.w("TAG", "Error updating document", e) }
            APIResult.APISuccess(data = null)
        } catch (e: Exception) {
            APIResult.APIFailure(errorMessage = "Failed")
        }
    }
}