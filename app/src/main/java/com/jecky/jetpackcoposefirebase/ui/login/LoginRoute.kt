package com.jecky.jetpackcoposefirebase.ui.login

import androidx.compose.runtime.Composable

@Composable
fun LoginRoute(loginClicked: () -> Unit, registerClicked: () -> Unit) {
    LoginScreen(
        signInClicked = loginClicked, registerClicked = registerClicked
    )
}