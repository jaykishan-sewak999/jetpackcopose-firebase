package com.jecky.jetpackcoposefirebase.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jecky.jetpackcoposefirebase.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(color = Color.Blue), contentAlignment = Alignment.TopCenter
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = painterResource(id = R.drawable.ic_action_setting),
                    contentDescription = "logo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_action_edit),
                    contentDescription = "logo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.user_placeholder),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "test@gmail.com",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    ProfileScreen()
}