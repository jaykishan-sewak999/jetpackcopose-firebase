package com.jecky.jetpackcoposefirebase.ui.hometab

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jecky.jetpackcoposefirebase.R
import com.jecky.jetpackcoposefirebase.repository.model.BottomNavigationItem

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
        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(selected = currRoute == item.route, onClick = {
                navigator.navigate(item.route) {
                    navigator.graph.startDestinationRoute?.let { screen_route ->
                       /* popUpTo(screen_route) {
                            saveState = false
                        }*/
                        popUpTo(0)
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                alwaysShowLabel = true,

                label = { Text(text = item.title) })
        }
    }
}
