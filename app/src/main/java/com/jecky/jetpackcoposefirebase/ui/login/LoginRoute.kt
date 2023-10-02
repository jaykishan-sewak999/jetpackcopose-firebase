package com.jecky.jetpackcoposefirebase.ui.login

import androidx.compose.runtime.Composable

@Composable
fun LoginRoute(signInSuccess: () -> Unit, registerClicked: () -> Unit) {
    LoginScreen(
        signInSuccess = signInSuccess, registerClicked = registerClicked
    )
}