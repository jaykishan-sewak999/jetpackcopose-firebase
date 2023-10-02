package com.jecky.jetpackcoposefirebase.ui.hometab

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Category,
        BottomNavigationItem.Profile
    )
    val navBackStackEntry by navigator.currentBackStackEntryAsState()
    val currRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_700),
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(selected = currRoute == item.route, onClick = { /*TODO*/ },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                label = { Text(text = item.title) })
        }
    }
}
