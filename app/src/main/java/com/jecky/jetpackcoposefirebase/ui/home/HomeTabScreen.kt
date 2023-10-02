package com.jecky.jetpackcoposefirebase.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jecky.jetpackcoposefirebase.NavGraph
import com.jecky.jetpackcoposefirebase.R
import com.jecky.jetpackcoposefirebase.repository.model.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationComponent(navigator = navController) }) {
        NavGraph(navController = navController)
    }
}

@Composable
fun BottomNavigationComponent(navigator: NavHostController) {
    val items = listOf(BottomNavigationItem.Home)
    BottomNavigation() {
        
    }

}


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)

    ) {

    }
}