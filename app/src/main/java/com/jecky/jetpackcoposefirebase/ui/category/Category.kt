package com.jecky.jetpackcoposefirebase.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jecky.jetpackcoposefirebase.ui.theme.md_theme_light_onPrimaryContainer

@Composable
fun CategoryScreen(CategoryClicked: (String) -> Unit) {
    val categoryViewModel: CategoryViewModel = viewModel(factory = CategoryViewModelFactory())
    val categories by categoryViewModel.categories.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        categoryViewModel.getCategories()
    }
    if (categoryViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = md_theme_light_onPrimaryContainer)
        }
    } else {

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { quote ->
                Card(modifier = Modifier.clickable {
                    CategoryClicked(quote.id)
                }) {
                        Text(
                            text = quote.name,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                }
                Divider(color = Color.Transparent, thickness = 10.dp)
            }
        }
    }

}
