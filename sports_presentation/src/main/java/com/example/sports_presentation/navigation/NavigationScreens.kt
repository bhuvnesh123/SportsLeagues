package com.example.sports_presentation.navigation

private const val COUNTRY_LIST_ROUTE = " country_list"
private const val COUNTRY_LEAGUES_ROUTE = "country_leagues"

sealed class NavigationScreens(val route: String) {

    object CountryListScreen : NavigationScreens(route = COUNTRY_LIST_ROUTE)

    object CountryLeaguesScreen : NavigationScreens(route = COUNTRY_LEAGUES_ROUTE)

    companion object {
        const val COUNTRY_NAME_ARG = "countryName"
        fun createRoute(navigationScreen: NavigationScreens, param: String): String {
            return "${navigationScreen.route}/{$param}"
        }
    }
}
