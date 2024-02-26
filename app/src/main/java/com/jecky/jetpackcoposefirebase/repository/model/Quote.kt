package com.jecky.jetpackcoposefirebase.repository.model

data class Quote(
    val quote: String? = null,
    val author: String? = null,
    val categoryId: String? = null,
    val userId: String? = null,
    var id: String? = null,
    var isFavorite: Boolean? = false
)
