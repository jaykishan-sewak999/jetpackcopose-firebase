package com.jecky.jetpackcoposefirebase.network

sealed class APIResult<T>(val data: T? = null, val errorMessage: String? = null) {

    object Loading : APIResult<Boolean>()
    object NONE : APIResult<Boolean>()
    class APISuccess<T>(data: T?): APIResult<T>(data = data)
    class APIFailure<T>(errorMessage: String?): APIResult<T>(errorMessage = errorMessage)
}