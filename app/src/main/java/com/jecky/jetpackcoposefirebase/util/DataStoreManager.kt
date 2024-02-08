package com.jecky.jetpackcoposefirebase.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val MERCHANT_DATASTORE ="firebase_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MERCHANT_DATASTORE)

class DataStoreManager(private val context: Context) {

    companion object{
        val LOGGED_IN_USER_ID = stringPreferencesKey("firebase_id")
    }

    suspend fun saveToDataStore(value: String){
        context.dataStore.edit {
            it[LOGGED_IN_USER_ID] = value
        }
    }
    fun getDataFromStore() = context.dataStore.data.map {
        it[LOGGED_IN_USER_ID] ?: ""
    }

    suspend fun clearDataStore(){
        context.dataStore.edit {
            it.clear()
        }
    }
}