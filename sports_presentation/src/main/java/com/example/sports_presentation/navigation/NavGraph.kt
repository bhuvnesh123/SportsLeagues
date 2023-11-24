package com.example.sports_presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sports_presentation.screens.countieslist.components.CountryListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.CountryListScreen.route
    ) {

        composable(NavigationScreens.CountryListScreen.route) {
            CountryListScreen()
        }

        composable(NavigationScreens.CountryLeaguesScreen.route) {
             // TODO
        }

    }
}