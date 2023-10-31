package com.jecky.jetpackcoposefirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.model.Quote
import com.jecky.jetpackcoposefirebase.util.AppConstants
import kotlinx.coroutines.tasks.await

class QuotesRepository {

    var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun addQuote(quote: Quote): APIResult<Boolean> {
        return try {
            val apiResponse = fireStore.collection(AppConstants.TABLE_QUOTE).add(quote).await()
            APIResult.APISuccess(data = true)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }


    suspend fun getQuotes(): APIResult<List<Quote>> {
        return try {
            val quoteList = arrayListOf<Quote>()
            val apiResult = fireStore.collection(AppConstants.TABLE_QUOTE).get().await()
            for (document in apiResult.documents) {
                val quote = document.toObject(Quote::class.java)
                if (quote != null) {
                    quoteList.add(quote)
                }
            }
            APIResult.APISuccess(data = null)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }
}