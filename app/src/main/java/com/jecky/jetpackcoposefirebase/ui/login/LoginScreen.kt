package com.jecky.jetpackcoposefirebase.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jecky.jetpackcoposefirebase.R
import com.jecky.jetpackcoposefirebase.ui.theme.md_theme_light_error
import com.jecky.jetpackcoposefirebase.util.EmailFieldState
import com.jecky.jetpackcoposefirebase.util.PasswordFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(signInSuccess: () -> Unit, registerClicked: () -> Unit) {
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

            label = {
                Text(text = "Email")
            },
            isError = emailFieldState.shouldShoeError(),
        )

        emailFieldState.showError()?.let {
            Text(
                text = it, style = TextStyle(color = md_theme_light_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 40.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = passwordFieldState.text, onValueChange = {
                passwordFieldState.text = it
            }, colors = TextFieldDefaults.outlinedTextFieldColors(
                errorBorderColor =  md_theme_light_error,
            ), label = {
                Text(text = "Password")
            }, isError = passwordFieldState.shouldShoeError(),
            enabled = true
        )
        passwordFieldState.showError()?.let {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = it, style = TextStyle(/*color = Color.Red*/)
                )
            }
        }
        val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory())
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                loginViewModel.doLogin(emailFieldState.text, passwordFieldState.text)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            enabled = emailFieldState.isValid && passwordFieldState.isValid
        ) {
            if (loginViewModel.loading) {
                CircularProgressIndicator()
            } else Text(text = "Login")
        }
        if (loginViewModel.loginState != 0) {
            if (loginViewModel.doLogin.value == null) {
                Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                loginViewModel.loginState = 0
            } else {
                loginViewModel.loginState = 0
                Toast.makeText(context, "Login success", Toast.LENGTH_LONG).show()
                signInSuccess()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Text(text = "Don't have account? ")
            Text(text = "Sign Up", style = TextStyle(/*color = Color.Blue*/),
                modifier = Modifier.clickable {
                    registerClicked()
                })
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(signInSuccess = {

    }, registerClicked = {

    })
}