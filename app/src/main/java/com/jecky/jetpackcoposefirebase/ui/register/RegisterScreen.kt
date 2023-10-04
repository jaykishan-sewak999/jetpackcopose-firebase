package com.jecky.jetpackcoposefirebase.ui.register

import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jecky.jetpackcoposefirebase.R
import com.jecky.jetpackcoposefirebase.util.ConfirmPasswordFieldState
import com.jecky.jetpackcoposefirebase.util.EmailFieldState
import com.jecky.jetpackcoposefirebase.util.PasswordFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(loginClicked: () -> Unit, registerSuccess: () -> Unit) {
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
        Spacer(modifier = Modifier.height(15.dp))

        val emailFieldState by remember { mutableStateOf(EmailFieldState()) }
        val passFieldState by remember { mutableStateOf(PasswordFieldState()) }
        val confirmPasswordFieldState by remember {
            mutableStateOf(ConfirmPasswordFieldState(passFieldState))
        }
        EmailInputComponent(emailFieldState)
        PasswordComponent(passFieldState)

        Spacer(modifier = Modifier.height(15.dp))
        ConfirmPasswordComponent(confirmPasswordFieldState)

        Spacer(modifier = Modifier.height(15.dp))
        val signUpViewModel: RegisterViewModel = viewModel(factory = RegisterViewModelFactory())

        val context = LocalContext.current
        LoginButton(signUpViewModel, emailFieldState, passFieldState, confirmPasswordFieldState)
        if (signUpViewModel.loginState != 0) {
            if (signUpViewModel.doRegister.value == null) {
                Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show()
                signUpViewModel.loginState = 0
            } else {
                Toast.makeText(context, "You are successfully registered", Toast.LENGTH_LONG).show()
                signUpViewModel.loginState = 0
               /* signUpViewModel.addUserData(
                    email = emailFieldState.text,
                    userId = signUpViewModel.doRegister.value!!.user?.uid!!
                )*/
                registerSuccess()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Text(text = "Already have account? ")
            Text(text = "Sign In", style = TextStyle(color = Color.Blue))
        }
    }
}

@Composable
fun LoginButton(
    signUpViewModel: RegisterViewModel,
    emailFieldState: EmailFieldState,
    passFieldState: PasswordFieldState,
    confirmPasswordFieldState: ConfirmPasswordFieldState
) {
    Button(
        onClick = {
            signUpViewModel.doLogin(emailFieldState.text, passFieldState.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        enabled = emailFieldState.isValid && passFieldState.isValid && confirmPasswordFieldState.isValid
    ) {
        if (signUpViewModel.showLoading) {
            CircularProgressIndicator(color = Color.Cyan)
        } else Text(text = "Register")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInputComponent(emailFieldState: EmailFieldState) {
    OutlinedTextField(value = emailFieldState.text, onValueChange = {
        emailFieldState.text = it
    }, colors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.Gray, focusedBorderColor = Color.Cyan
    ), label = {
        Text(text = "Email")
    })
    emailFieldState.showError()?.let {
        Text(
            text = it,
            style = TextStyle(color = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordComponent(passFieldState: PasswordFieldState) {
    OutlinedTextField(
        value = passFieldState.text,
        onValueChange = {
            passFieldState.text = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray, focusedBorderColor = Color.Cyan
        ),
        label = {
            Text(text = "Password")
        },
    )
    passFieldState.showError()?.let {
        Text(
            text = it,
            style = TextStyle(color = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordComponent(passFieldState: ConfirmPasswordFieldState) {
    OutlinedTextField(
        value = passFieldState.text,
        onValueChange = {
            passFieldState.text = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray, focusedBorderColor = Color.Cyan
        ),
        label = {
            Text(text = "Password")
        },
    )
    passFieldState.showError()?.let {
        Text(
            text = it,
            style = TextStyle(color = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    RegisterScreen(loginClicked = {

    }, registerSuccess = {

    })
}