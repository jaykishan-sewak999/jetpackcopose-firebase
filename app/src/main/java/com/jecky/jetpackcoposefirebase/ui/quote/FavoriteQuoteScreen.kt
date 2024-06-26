package com.jecky.jetpackcoposefirebase.ui.quote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jecky.jetpackcoposefirebase.ui.theme.md_theme_light_onPrimaryContainer

@Composable
fun FavoriteQuoteScreen() {
    val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModelFactory())
    val quotesList by quoteViewModel.quoteList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        quoteViewModel.getMyFavoriteQuotes()

    }
    if (quoteViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = md_theme_light_onPrimaryContainer)
        }
    } else {
        if (quotesList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No data found",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(quotesList) { quote ->
                    QuoteItem(quote, addToFav = { quoteId ->
                        quoteViewModel.addQuoteToFavorite(quoteId)
                    }, removeFromFav = { quoteId ->
                        quoteViewModel.removeQuoteToFavorite(quoteId)
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    FavoriteQuoteScreen()
}
