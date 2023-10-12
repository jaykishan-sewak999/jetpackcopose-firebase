package com.jecky.jetpackcoposefirebase.util

open class EmailFieldState : TextFieldState(validator = ::isEmailValid, errorMessage = ::inValidEmailError, changeFocus = ::hasFocus)

fun isEmailValid(email:String): Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun inValidEmailError(): String{
    return "Invalid email"
}

fun hasFocus(hasFocus: Boolean): Boolean{
    return hasFocus
}