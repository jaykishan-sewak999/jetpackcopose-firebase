package com.jecky.jetpackcoposefirebase.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import com.jecky.jetpackcoposefirebase.ui.quote.QuoteViewModel
import com.jecky.jetpackcoposefirebase.ui.quote.QuoteViewModelFactory
import com.jecky.jetpackcoposefirebase.ui.theme.md_theme_light_onPrimary

@Composable
fun HomeScreen() {
    val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModelFactory())

    val quotesList by quoteViewModel.quoteList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        quoteViewModel.getQuotes()
    }
    if (quoteViewModel.loading) {
        CircularProgressIndicator(color = md_theme_light_onPrimary)
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(quotesList) { quote ->
                    Text(
                        text = quote.quote!!,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Text(
                        text = quote.author!!,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
