package com.jecky.jetpackcoposefirebase.ui.register

import androidx.compose.runtime.Composable

@Composable
fun RegisterRoute(registerClicked: () -> Unit, loginClicked : () -> Unit){
    RegisterScreen(loginClicked = loginClicked, registerClicked = registerClicked)
}