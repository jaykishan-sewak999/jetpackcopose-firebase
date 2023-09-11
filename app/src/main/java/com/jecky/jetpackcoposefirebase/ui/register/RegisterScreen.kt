package com.jecky.jetpackcoposefirebase.ui.register

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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jecky.jetpackcoposefirebase.R
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(loginClicked: () -> Unit) {
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
        OutlinedTextField(value = "", onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Cyan
        ),
        label = {
            Text(text = "Email")
        })
        OutlinedTextField(value = "", onValueChange = {},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Cyan
            ),
            label = {
                Text(text = "Password")
            },
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)) {
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
    RegisterScreen(loginClicked = {

    })
}