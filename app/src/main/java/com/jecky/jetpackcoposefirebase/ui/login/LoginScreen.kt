package com.jecky.jetpackcoposefirebase.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(loginClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        
    }

}

@Preview
@Composable
fun PreviewLoginScreen(){
    LoginScreen (loginClicked = {

    })
}