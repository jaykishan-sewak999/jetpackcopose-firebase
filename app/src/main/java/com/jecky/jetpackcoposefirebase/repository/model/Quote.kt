package com.jecky.jetpackcoposefirebase.repository.model

/**
 * Quote data class for fetching quotes
 */
data class Quote(
    val quote: String? = null,
    val author: String? = null,
    val categoryId: String? = null,
    val userId: String? = null,
    var id: String? = null,
    var isFavorite: Boolean? = false
)
