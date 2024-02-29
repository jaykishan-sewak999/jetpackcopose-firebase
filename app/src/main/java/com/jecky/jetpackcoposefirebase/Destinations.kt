package com.jecky.jetpackcoposefirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jecky.jetpackcoposefirebase.Destinations.ADD_QUOTE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.CATEGORY_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.HOME_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.LOGIN_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.MY_FAVORITE_QUOTE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.PROFILE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.REGISTER_SCREEN
import com.jecky.jetpackcoposefirebase.ui.category.CategoryRoute
import com.jecky.jetpackcoposefirebase.ui.favorite.FavoriteQuoteScreen
import com.jecky.jetpackcoposefirebase.ui.home.HomeScreen
import com.jecky.jetpackcoposefirebase.ui.login.LoginRoute
import com.jecky.jetpackcoposefirebase.ui.profile.ProfileScreen
import com.jecky.jetpackcoposefirebase.ui.quote.AddQuoteScreen
import com.jecky.jetpackcoposefirebase.ui.register.RegisterRoute
import com.jecky.jetpackcoposefirebase.util.AppConstants.ADD_QUOTE_ID
import com.jecky.jetpackcoposefirebase.util.AppConstants.LOG_OUT_ID
import com.jecky.jetpackcoposefirebase.util.AppConstants.MY_FAVORITES_ID
import com.jecky.jetpackcoposefirebase.util.AppConstants.MY_QUOTE_ID

object Destinations {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val HOME_SCREEN = "home/{category}/{fetchMyQuotes}"
    const val PROFILE_SCREEN = "profile"
    const val CATEGORY_SCREEN = "category"
    const val ADD_QUOTE_SCREEN = "add_quote"
    const val MY_FAVORITE_QUOTE_SCREEN = "favorite_quote"
}

@Composable
fun NavGraph(navController: NavHostController, isSplash: Boolean) {
    NavHost(navController = navController, startDestination = if (isSplash){LOGIN_SCREEN} else {"home/null/false"}) {
        composable(LOGIN_SCREEN) {
            LoginRoute(signInSuccess = {
                navController.navigate(HOME_SCREEN){
                    popUpTo(LOGIN_SCREEN) {
                        inclusive = true
                    }
                }
            }, registerClicked = {
                navController.navigate(REGISTER_SCREEN)
            })
        }
        composable(REGISTER_SCREEN) {
            RegisterRoute(registerSuccess = {
                navController.navigate(HOME_SCREEN){
                    popUpTo(LOGIN_SCREEN) {
                        inclusive = true
                    }
                }
            }, loginClicked = {
                navController.navigate(LOGIN_SCREEN)
            })
        }
        composable(HOME_SCREEN) {
            val category = it.arguments?.getString("category") ?: "{category}"
            val fetchMyQuotes = it.arguments?.getString("fetchMyQuotes")
            HomeScreen(category,fetchMyQuotes?.toBoolean())
        }
        composable(PROFILE_SCREEN) {
            ProfileScreen(onItemClicked = {
                when (it) {
                    MY_QUOTE_ID -> navController.navigate("home/null/true")
                    ADD_QUOTE_ID -> navController.navigate(ADD_QUOTE_SCREEN)
                    MY_FAVORITES_ID -> navController.navigate(MY_FAVORITE_QUOTE_SCREEN)
                    LOG_OUT_ID ->  navController.navigate(LOGIN_SCREEN){
                        popUpTo(0)
                    }
                }
            })
        }
        composable(CATEGORY_SCREEN) {
            CategoryRoute(CategoryClicked = { categoryId ->
                navController.navigate("home/$categoryId/false")
            })
        }
        composable(ADD_QUOTE_SCREEN) {
            AddQuoteScreen(onBackPress = {
                navController.popBackStack()
            })
        }
        composable(MY_FAVORITE_QUOTE_SCREEN) {
            FavoriteQuoteScreen()
        }
    }
}