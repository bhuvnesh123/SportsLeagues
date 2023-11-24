package com.example.sports_presentation.navigation

import com.example.sports_presentation.constants.Constants.COUNTRY_LEAGUES_ROUTE
import com.example.sports_presentation.constants.Constants.COUNTRY_LIST_ROUTE

sealed class NavigationScreens(val route: String) {

    object CountryListScreen : NavigationScreens(route = COUNTRY_LIST_ROUTE)

    object CountryLeaguesScreen : NavigationScreens(route = COUNTRY_LEAGUES_ROUTE)
}