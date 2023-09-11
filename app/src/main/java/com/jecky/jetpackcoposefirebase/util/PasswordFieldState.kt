package com.jecky.jetpackcoposefirebase.util

class PasswordFieldState :
    TextFieldState(validator = ::isValidPassword, errorMessage = ::inValidPasswordError)

class ConfirmPasswordFieldState(val passwordFieldState: PasswordFieldState) :
    TextFieldState(validator = ::isValidPassword, errorMessage = ::inValidEmailError) {

    override val isValid: Boolean
        get() = isPasswordAndConfirmPasswordSame(text, passwordFieldState.text)

    override fun showError(): String? {
        return if (text.length > 2 && !isPasswordAndConfirmPasswordSame(
                text,
                passwordFieldState.text
            )
        )
            passwordNotSame()
        else
            null
    }
}

fun isValidPassword(password: String): Boolean {
    return password.length > 6
}

fun inValidPasswordError(): String {
    return "Invalid password"
}

fun passwordNotSame(): String {
    return "Password and confirm password are not same"
}


fun isPasswordAndConfirmPasswordSame(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}