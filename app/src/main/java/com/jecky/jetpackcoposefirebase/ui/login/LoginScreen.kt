package com.jecky.jetpackcoposefirebase.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jecky.jetpackcoposefirebase.R
import com.jecky.jetpackcoposefirebase.util.EmailFieldState
import com.jecky.jetpackcoposefirebase.util.PasswordFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_placeholder),
            contentDescription = "User",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        val emailFieldState by remember { mutableStateOf(EmailFieldState()) }
        val passwordFieldState by remember {
            mutableStateOf(PasswordFieldState())
        }

        OutlinedTextField(
            value = emailFieldState.text,
            onValueChange = {
                emailFieldState.text = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Cyan,
                errorBorderColor = Color.Red
            ),
            label = {
                Text(text = "Email")
            },
            isError = emailFieldState.shouldShoeError(),

            )

        emailFieldState.showError()?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    style = TextStyle(color = Color.Red)
                )
            }
        }


        OutlinedTextField(
            value = passwordFieldState.text, onValueChange = {
                passwordFieldState.text = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Cyan
            ),
            label = {
                Text(text = "Password")
            },
            isError = passwordFieldState.shouldShoeError()
        )
        passwordFieldState.showError()?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    style = TextStyle(color = Color.Red)
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Text(text = "Already have account? ")
            Text(text = "Sign Up", style = TextStyle(color = Color.Blue))

        }
    }

}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(loginClicked = {

    })
}