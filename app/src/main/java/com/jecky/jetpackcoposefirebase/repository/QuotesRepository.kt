package com.jecky.jetpackcoposefirebase.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.model.Quote
import com.jecky.jetpackcoposefirebase.util.AppConstants
import com.jecky.jetpackcoposefirebase.util.AppConstants.FIELD_CATEGORY
import com.jecky.jetpackcoposefirebase.util.AppConstants.FIELD_USER_ID
import kotlinx.coroutines.tasks.await

class QuotesRepository {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

    suspend fun addQuote(quote: Quote): APIResult<Boolean> {
        return try {
            val apiResponse = fireStore.collection(AppConstants.TABLE_QUOTE).add(quote).await()
            APIResult.APISuccess(data = true)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }

    suspend fun getQuotes(category: String?): APIResult<List<Quote>> {
        return try {
            var apiResult: QuerySnapshot? = null
            val quoteList = arrayListOf<Quote>()
            apiResult = if (category != "{category}") {
                fireStore.collection(AppConstants.TABLE_QUOTE)
                    .whereEqualTo(FIELD_CATEGORY, category)
                    .get()
                    .await()
            } else {
                fireStore.collection(AppConstants.TABLE_QUOTE)
                    .get()
                    .await()
            }
            var response: DocumentSnapshot? = null
            response =
                fireStore.collection(AppConstants.TABLE_USER).document(currentFirebaseUser?.uid!!)
                    .get()
                    .await()

            val favList = response.data?.get("favorites") as ArrayList<String>
            for (document in apiResult.documents) {
                val quote = document.toObject(Quote::class.java)
                quote?.id = document.id
                quote?.isFavorite = favList.contains(quote?.id)
                if (quote != null) {
                    quoteList.add(quote)
                }
            }
            APIResult.APISuccess(data = quoteList)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }

    suspend fun getMyQuotes(): APIResult<List<Quote>> {
        return try {
            var apiResult: QuerySnapshot? = null
            val quoteList = arrayListOf<Quote>()
            apiResult =
                fireStore.collection(AppConstants.TABLE_QUOTE)
                    .whereEqualTo(
                        FIELD_USER_ID,
                        FirebaseAuth.getInstance().currentUser?.uid
                    )
                    .get()
                    .await()

            if (apiResult != null) {
                for (document in apiResult.documents) {
                    val quote = document.toObject(Quote::class.java)
                    if (quote != null) {
                        quoteList.add(quote)
                    }
                }
            }
            APIResult.APISuccess(data = quoteList)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }

    suspend fun getMyFavoriteQuotes(): APIResult<List<Quote>> {
        return try {
            var quoteApiResult: QuerySnapshot? = null
            val quoteList = arrayListOf<Quote>()

            var favQuoteResponse: DocumentSnapshot? = null
            favQuoteResponse =
                fireStore.collection(AppConstants.TABLE_USER).document(currentFirebaseUser?.uid!!)
                    .get()
                    .await()

            val favList = favQuoteResponse.data?.get("favorites") as ArrayList<String>

            quoteApiResult =
                fireStore.collection(AppConstants.TABLE_QUOTE)
                    .whereIn(
                        FieldPath.documentId(),
                        favList
                    )
                    .get()
                    .await()

            if (quoteApiResult != null) {
                for (document in quoteApiResult.documents) {
                    val quote = document.toObject(Quote::class.java)
                    quote?.id = document.id
                    quote?.isFavorite = true
                    if (quote != null) {
                        quoteList.add(quote)
                    }
                }
            }

            APIResult.APISuccess(data = quoteList)
        } catch (exception: Exception) {
            APIResult.APIFailure(errorMessage = exception.message)
        }
    }
}