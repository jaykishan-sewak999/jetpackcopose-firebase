package com.jecky.jetpackcoposefirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jecky.jetpackcoposefirebase.Destinations.LOGIN_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.REGISTER_SCREEN
import com.jecky.jetpackcoposefirebase.ui.login.LoginRoute
import com.jecky.jetpackcoposefirebase.ui.login.LoginScreen
import com.jecky.jetpackcoposefirebase.ui.register.RegisterRoute
import com.jecky.jetpackcoposefirebase.ui.register.RegisterScreen

object Destinations {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(LOGIN_SCREEN) {
            LoginRoute(loginClicked = {

            }, registerClicked = {
                navController.navigate(REGISTER_SCREEN)
            })
        }
        composable(REGISTER_SCREEN) {
          RegisterRoute(registerClicked = {

          }, loginClicked = {

          })
        }
    }
}