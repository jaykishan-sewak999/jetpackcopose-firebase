package com.jecky.jetpackcoposefirebase.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(
    val validator: (String) -> Boolean = { false },
    val errorMessage: () -> String = { "" }
) {

    var text: String by mutableStateOf("")
    val hasError: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    open fun showError(): String? {
        return if (!isValid && text.length > 2) {
            errorMessage()
        } else {
            null
        }
    }

    open fun shouldShoeError() = isValid && hasError
}