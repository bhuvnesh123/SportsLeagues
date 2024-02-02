package com.example.sports_presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sports_presentation.common.component.ProvideDimens
import com.example.sports_presentation.common.smallDimensions
import com.example.sports_presentation.common.sw360Dimensions
import com.example.sports_presentation.navigation.NavigationScreens.Companion.COUNTRY_NAME_ARG
import com.example.sports_presentation.screens.countieslist.components.CountryListScreenLayout
import com.example.sports_presentation.screens.countryleagues.components.LeaguesScreenLayout

// Composable function that defines the navigation graph of the app
@Composable
fun NavGraph(navController: NavHostController, onBackFromHome: () -> Unit) {
    val configuration = LocalConfiguration.current // Get the current device configuration
    // Set dimensions based on screen width
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    // Define the navigation graph
    NavHost(
        navController = navController, // NavController object
        startDestination = NavigationScreens.CountryListScreen.route, // Starting screen
    ) {
        composable(route = NavigationScreens.CountryListScreen.route) {
            // Provide dimensions to children
            ProvideDimens(dimensions = dimensions) {
                CountryListScreenLayout(callback = {
                    navController.navigate(route = "${NavigationScreens.CountryLeaguesScreen.route}/$it")
                }, onBack = {
                    onBackFromHome()
                })
            }
        }

        composable(
            route = NavigationScreens.createRoute(
                navigationScreen = NavigationScreens.CountryLeaguesScreen,
                param = COUNTRY_NAME_ARG,
            ),
            arguments = listOf(
                navArgument(
                    name = COUNTRY_NAME_ARG,
                ) { type = NavType.StringType },
            ),
        ) {
            ProvideDimens(dimensions = dimensions) {
                LeaguesScreenLayout(onBack = {
                    navController.popBackStack()
                })
            }
        }
    }
}
