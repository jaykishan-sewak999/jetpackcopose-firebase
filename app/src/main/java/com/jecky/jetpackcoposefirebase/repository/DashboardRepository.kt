package com.jecky.jetpackcoposefirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.model.Category
import com.jecky.jetpackcoposefirebase.util.AppConstants
import kotlinx.coroutines.tasks.await

class DashboardRepository {
    var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getCategories(): APIResult<List<Category>> {
        return try {
            val categoryList = arrayListOf<Category>()
            val userResponse =
                fireStore.collection(AppConstants.TABLE_CATEGORY).get().await()

            for (document in userResponse.documents){
                val category = document.toObject(Category::class.java)
                if (category != null) {
                    category.id = document.id
                    categoryList.add(category)
                }
            }

            APIResult.APISuccess(data = categoryList)
        } catch (e: Exception) {
            APIResult.APIFailure(errorMessage = e.message)
        }
    }
}