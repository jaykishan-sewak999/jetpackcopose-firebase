package com.jecky.jetpackcoposefirebase.repository.model

import com.jecky.jetpackcoposefirebase.R

sealed class BottomNavigationItem(val title: String, val icon: Int, val route: String){
    object Home: BottomNavigationItem(title = "Home", icon = R.drawable.ic_action_home_filled, route = "home")
    object Category: BottomNavigationItem(title = "Category", icon = R.drawable.ic_action_category_out_lined, route = "category")
    object Profile: BottomNavigationItem(title = "Profile", icon = R.drawable.ic_action_profile_outlined, route = "profile")
}
