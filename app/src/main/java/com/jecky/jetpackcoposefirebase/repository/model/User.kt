package com.jecky.jetpackcoposefirebase.repository.model

data class User(var userId: String, val userName: String, val favorites: ArrayList<String>?= ArrayList<String>())
