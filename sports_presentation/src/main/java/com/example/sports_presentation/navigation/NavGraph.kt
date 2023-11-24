package com.example.sports_presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sports_presentation.common.component.ProvideDimens
import com.example.sports_presentation.common.smallDimensions
import com.example.sports_presentation.common.sw360Dimensions
import com.example.sports_presentation.screens.countieslist.components.CountryListScreenBaseLayout

// Composable function that defines the navigation graph of the app
@Composable
fun NavGraph(navController: NavHostController) {
    val configuration = LocalConfiguration.current // Get the current device configuration
    // Set dimensions based on screen width
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    // Define the navigation graph
    NavHost(
        navController = navController, // NavController object
        startDestination = NavigationScreens.CountryListScreen.route // Starting screen
    ) {
        composable(NavigationScreens.CountryListScreen.route) {
            // Provide dimensions to children
            ProvideDimens(dimensions = dimensions) {
                CountryListScreenBaseLayout(callback = {
                    // TODO navigate to CountryLeaguesScreen
                })
            }
        }

        composable(NavigationScreens.CountryLeaguesScreen.route) {
            // TODO
        }
    }
}

