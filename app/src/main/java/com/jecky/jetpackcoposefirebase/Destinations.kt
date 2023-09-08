package com.jecky.jetpackcoposefirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jecky.jetpackcoposefirebase.Destinations.LOGIN_SCREEN

object Destinations {
    const val LOGIN_SCREEN = "login"
}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(LOGIN_SCREEN) {

        }
    }
}