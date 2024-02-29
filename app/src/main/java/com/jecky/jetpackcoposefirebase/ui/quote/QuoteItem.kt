package com.jecky.jetpackcoposefirebase.ui.quote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jecky.jetpackcoposefirebase.repository.model.Quote

@Composable
fun QuoteItem(quote: Quote, addToFav: (String) -> Unit, removeFromFav: (String) -> Unit) {
    Card {
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)) {
            Text(
                text = quote.quote!!,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = quote.author!!,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray)
            Spacer(modifier = Modifier.height(5.dp))
            var isFavorite by remember {
                mutableStateOf(quote.isFavorite)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (isFavorite == true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .clickable {
                            isFavorite = isFavorite?.not()
                            if (isFavorite == true) {
                                addToFav(quote.id!!)
                            } else {
                                removeFromFav(quote.id!!)
                            }
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Share",
                    tint = Color.Black,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
    Divider(color = Color.Transparent, thickness = 10.dp)
}