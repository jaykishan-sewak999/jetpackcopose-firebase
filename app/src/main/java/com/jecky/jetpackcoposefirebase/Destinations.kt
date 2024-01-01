package com.jecky.jetpackcoposefirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jecky.jetpackcoposefirebase.Destinations.ADD_QUOTE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.CATEGORY_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.HOME_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.LOGIN_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.PROFILE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.REGISTER_SCREEN
import com.jecky.jetpackcoposefirebase.ui.category.CategoryRoute
import com.jecky.jetpackcoposefirebase.ui.home.HomeScreen
import com.jecky.jetpackcoposefirebase.ui.login.LoginRoute
import com.jecky.jetpackcoposefirebase.ui.profile.ProfileScreen
import com.jecky.jetpackcoposefirebase.ui.quote.AddQuoteScreen
import com.jecky.jetpackcoposefirebase.ui.register.RegisterRoute
import com.jecky.jetpackcoposefirebase.util.AppConstants.ADD_QUOTE_ID
import com.jecky.jetpackcoposefirebase.util.AppConstants.MY_QUOTE_ID

object Destinations {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val HOME_TAB_SCREEN = "home_tab"
    const val HOME_SCREEN = "home/{category}/{fetchMyQuotes}"
    const val PROFILE_SCREEN = "profile"
    const val CATEGORY_SCREEN = "category"
    const val ADD_QUOTE_SCREEN = "add_quote"

}

@Composable
fun NavGraph(navController: NavHostController, isSplash: Boolean) {
    NavHost(navController = navController, startDestination = if (isSplash){LOGIN_SCREEN} else {HOME_SCREEN}) {
        composable(LOGIN_SCREEN) {
            LoginRoute(signInSuccess = {
                navController.navigate(HOME_SCREEN)

            }, registerClicked = {
                navController.navigate(REGISTER_SCREEN)
            })
        }
        composable(REGISTER_SCREEN) {
            RegisterRoute(registerSuccess = {
                navController.navigate(HOME_SCREEN)
            }, loginClicked = {
                navController.navigate(LOGIN_SCREEN)
            })
        }
   /*     composable(HOME_TAB_SCREEN) {
            HomeTabScreen(navController)
        }*/
        composable(HOME_SCREEN) {
            val category = it.arguments?.getString("category")
            val fetchMyQuotes = it.arguments?.getString("fetchMyQuotes")
            HomeScreen(category,fetchMyQuotes?.toBoolean())
        }
        composable(PROFILE_SCREEN) {
            ProfileScreen(onItemClicked = {
                when (it) {
                    MY_QUOTE_ID -> navController.navigate("home/null/true")
                    ADD_QUOTE_ID -> navController.navigate(ADD_QUOTE_SCREEN)
                }
            })
            //AddQuoteScreen()
        }
        composable(CATEGORY_SCREEN) {
            CategoryRoute(CategoryClicked = { categoryId ->
                navController.navigate("home/$categoryId/false")
            })
        }
        composable(ADD_QUOTE_SCREEN) {
            AddQuoteScreen()
        }
    }
}

@Composable
fun NavGraphDashboard(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable(HOME_SCREEN) {
            val category = it.arguments?.getString("category")
            val fetchMyQuotes = it.arguments?.getString("fetchMyQuotes")
            HomeScreen(category,fetchMyQuotes?.toBoolean())
        }
        composable(PROFILE_SCREEN) {
            ProfileScreen(onItemClicked = {
                when (it) {
                    MY_QUOTE_ID -> navController.navigate("home/null/true")
                    ADD_QUOTE_ID -> navController.navigate(ADD_QUOTE_SCREEN)
                }
            })
            //AddQuoteScreen()
        }
        composable(CATEGORY_SCREEN) {
            CategoryRoute(CategoryClicked = { categoryId ->
                navController.navigate("home/$categoryId/false")
            })
        }
        composable(ADD_QUOTE_SCREEN) {
            AddQuoteScreen()
        }
    }
}