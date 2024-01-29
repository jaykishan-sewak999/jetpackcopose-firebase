package com.jecky.jetpackcoposefirebase.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jecky.jetpackcoposefirebase.R
import com.jecky.jetpackcoposefirebase.ui.theme.md_theme_light_onSecondary
import com.jecky.jetpackcoposefirebase.ui.theme.purple_profile
import com.jecky.jetpackcoposefirebase.util.AppConstants.ADD_QUOTE_ID
import com.jecky.jetpackcoposefirebase.util.AppConstants.LOG_OUT_ID
import com.jecky.jetpackcoposefirebase.util.AppConstants.MY_QUOTE_ID

@Composable
fun ProfileScreen(onItemClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(170.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = purple_profile), contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_action_setting),
                        contentDescription = "logo",
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.user_placeholder),
                        contentDescription = "logo",
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_action_edit),
                        contentDescription = "logo",
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "test@gmail.com",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "+91 9033008545",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()

                .padding(15.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .background(color = md_theme_light_onSecondary),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                ProfileButtonItem(
                    icon = Icons.Outlined.Edit,
                    title = "Edit Profile",
                    onClick = {

                    })
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))
                ProfileButtonItem(
                    icon = Icons.Outlined.List,
                    title = "My Quote",
                    onClick = {
                        onItemClicked(MY_QUOTE_ID)
                    })
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))
                ProfileButtonItem(
                    icon = Icons.Outlined.AccountBox,
                    title = "Add Quote",
                    onClick = {
                        onItemClicked(ADD_QUOTE_ID)
                    })
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))
                ProfileButtonItem(
                    icon = Icons.Outlined.ExitToApp,
                    title = "Logout",
                    onClick = {
                        onItemClicked(LOG_OUT_ID)
                    })

            }
        }

    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    ProfileScreen(onItemClicked = {

    })
}

@Composable
fun ProfileButtonItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable {
            onClick()
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = purple_profile,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.Gray,
                fontSize = 18.sp
            )
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = "Arrow",
                tint = purple_profile,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }

    }
}