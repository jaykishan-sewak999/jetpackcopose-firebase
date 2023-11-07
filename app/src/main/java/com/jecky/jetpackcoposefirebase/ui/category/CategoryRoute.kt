package com.jecky.jetpackcoposefirebase.ui.category

import androidx.compose.runtime.Composable

@Composable
fun CategoryRoute(CategoryClicked: (String) -> Unit) {
    CategoryScreen(CategoryClicked = CategoryClicked)
}