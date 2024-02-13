package com.jecky.jetpackcoposefirebase.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.model.User
import com.jecky.jetpackcoposefirebase.util.AppConstants
import kotlinx.coroutines.tasks.await

class UserRepository {
    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

    suspend fun addUserData(user: User): APIResult<User> {
        return try {
            val userResponse =
                fireStore.collection(AppConstants.TABLE_USER).document(user.userId).set(user).await()
            APIResult.APISuccess(data = user.apply { userId = user.userId })
        } catch (e: Exception) {
            APIResult.APIFailure(errorMessage = e.message)
        }
    }

    suspend fun addFavoriteQuote(
        quoteId: String
    ): APIResult<Boolean> {
        return try {
            val response = fireStore.collection(AppConstants.TABLE_USER)
                .document(currentFirebaseUser?.uid!!)
                .update(AppConstants.FIELD_FAVORITES, FieldValue.arrayUnion(quoteId)).addOnSuccessListener {
                    Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener {
                    e -> Log.w("TAG", "Error updating document", e) }
            APIResult.APISuccess(data = null)
        } catch (e: Exception) {
            APIResult.APIFailure(errorMessage = "Failed")
        }
    }

    suspend fun removeFavoriteQuote(
        quoteId: String
    ): APIResult<Boolean> {
        return try {
            val response = fireStore.collection(AppConstants.TABLE_USER)
                .document(currentFirebaseUser?.uid!!)
                .update(AppConstants.FIELD_FAVORITES, FieldValue.arrayRemove(quoteId)).addOnSuccessListener {
                    Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener {
                        e -> Log.w("TAG", "Error updating document", e) }
            APIResult.APISuccess(data = null)
        } catch (e: Exception) {
            APIResult.APIFailure(errorMessage = "Failed")
        }
    }
}