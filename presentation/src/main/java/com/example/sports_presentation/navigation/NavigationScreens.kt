package com.example.sports_presentation.navigation

private const val COUNTRY_LIST_ROUTE = " country_list"
private const val COUNTRY_LEAGUES_ROUTE = "country_leagues"

enum class NavigationScreens(val route: String) {

    CountryListScreen(route = COUNTRY_LIST_ROUTE),

    CountryLeaguesScreen(route = COUNTRY_LEAGUES_ROUTE),
    ;

    companion object {
        const val COUNTRY_NAME_ARG = "countryName"
        fun createRoute(navigationScreen: NavigationScreens, param: String): String {
            return "${navigationScreen.route}/{$param}"
        }
    }
}
