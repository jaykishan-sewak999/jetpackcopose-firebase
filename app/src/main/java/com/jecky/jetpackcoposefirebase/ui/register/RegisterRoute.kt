package com.jecky.jetpackcoposefirebase.ui.register

import androidx.compose.runtime.Composable

@Composable
fun RegisterRoute(registerSuccess: () -> Unit, loginClicked : () -> Unit){
    RegisterScreen(loginClicked = loginClicked, registerSuccess = registerSuccess)
}