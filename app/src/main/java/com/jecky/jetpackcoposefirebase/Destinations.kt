package com.jecky.jetpackcoposefirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jecky.jetpackcoposefirebase.Destinations.CATEGORY_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.HOME_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.HOME_TAB_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.LOGIN_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.PROFILE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.REGISTER_SCREEN
import com.jecky.jetpackcoposefirebase.ui.category.CategoryRoute
import com.jecky.jetpackcoposefirebase.ui.home.HomeScreen
import com.jecky.jetpackcoposefirebase.ui.hometab.HomeTabScreen
import com.jecky.jetpackcoposefirebase.ui.login.LoginRoute
import com.jecky.jetpackcoposefirebase.ui.profile.ProfileScreen
import com.jecky.jetpackcoposefirebase.ui.register.RegisterRoute

object Destinations {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val HOME_TAB_SCREEN = "home_tab"
    const val HOME_SCREEN = "home/{category}"
    const val PROFILE_SCREEN = "profile"
    const val CATEGORY_SCREEN = "category"
}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(LOGIN_SCREEN) {
            LoginRoute(signInSuccess = {
                navController.navigate(HOME_TAB_SCREEN)

            }, registerClicked = {
                navController.navigate(REGISTER_SCREEN)
            })
        }
        composable(REGISTER_SCREEN) {
            RegisterRoute(registerSuccess = {
                navController.navigate(HOME_TAB_SCREEN)
            }, loginClicked = {
                navController.navigate(LOGIN_SCREEN)
            })
        }
        composable(HOME_TAB_SCREEN) {
            HomeTabScreen()
        }
    }
}

@Composable
fun NavGraphDashboard(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable(HOME_SCREEN) {
            val category = it.arguments?.getString("category")
            HomeScreen(category)
        }
        composable(PROFILE_SCREEN) {
            ProfileScreen()
            //AddQuoteScreen()
        }
        composable(CATEGORY_SCREEN) {
            CategoryRoute(CategoryClicked = { categoryId ->
                navController.navigate("home/$categoryId")
            })
        }
    }
}