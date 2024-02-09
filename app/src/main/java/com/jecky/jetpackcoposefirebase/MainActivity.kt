package com.jecky.jetpackcoposefirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jecky.jetpackcoposefirebase.Destinations.ADD_QUOTE_SCREEN
import com.jecky.jetpackcoposefirebase.Destinations.LOGIN_SCREEN
import com.jecky.jetpackcoposefirebase.ui.hometab.BottomNavigationComponent
import com.jecky.jetpackcoposefirebase.ui.theme.JetpackCoposeFirebaseTheme
import com.jecky.jetpackcoposefirebase.util.DataStoreManager

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackCoposeFirebaseTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                val showBottomBar = navController
                    .currentBackStackEntryAsState().value?.destination?.route != LOGIN_SCREEN && navController
                    .currentBackStackEntryAsState().value?.destination?.route != ADD_QUOTE_SCREEN
                val dataStoreManager = DataStoreManager(LocalContext.current)
                val fId = dataStoreManager.getDataFromStore().collectAsState(initial = "").value

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        AnimatedVisibility(
                            visible = showBottomBar,
                            enter = fadeIn() + scaleIn(),
                            exit = fadeOut() + scaleOut(),
                        ) {
                            BottomNavigationComponent(navigator = navController)
                        }
                    }) {
                        NavGraph(navController = navController, isSplash = fId.isEmpty())
                    }
                }
            }
        }
    }
}