package com.example.sports_presentation.screens.countryleagues.fakes

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.CountryLeaguesUseCase
import kotlinx.coroutines.delay

class FakesCountryLeaguesUseCase : CountryLeaguesUseCase {

    private val leagueModels = mapOf(
        "India" to listOf(
            LeagueModel(
                strSport = "Cricket",
                strLeague = "Indian Premier League",
                strCurrentSeason = "2023",
                intFormedYear = "2008",
                strDescriptionEN = "The Indian Premier League (IPL) is a professional Twenty20 cricket league in India contested during April and May of every year by franchise teams representing Indian cities. The league was founded by the Board of Control for Cricket in India (BCCI) in 2007. The title sponsor of the IPL in 2016 was Vivo Electronics, thus the league is officially known as the Vivo Indian Premier League. The current IPL title holders are Sunrisers Hyderabad.\r\n\r\nThe IPL is the most-attended cricket league in the world and ranks sixth among all sports leagues. In 2010, the IPL became the first sporting event in the world to be broadcast live on YouTube. The brand value of IPL was estimated to be US$4.5 billion in 2015 by American Appraisal, A Division of Duff & Phelps. According to BCCI, the 2015 IPL season contributed ₹11.5 billion (US$182 million) to the GDP of the Indian economy.\r\n\r\nAccording to global valuation and corporate finance advisor Duff & Phelps, the value of brand IPL has jumped to $4.16 billion after the 2016 edition, against $3.54 billion in 2015. The 19% jump is despite the fact that the US dollar to Indian rupee currency has depreciated by nearly 10%.",
                strTvRights = "US (Linear) - Willow [2017-2021]\r\nUS (Streaming) - ESPN+ [2021-2022]\r\nZA (Streaming) - SuperSport [2017-2023]\r\nUK (Streaming) - Sky Sports [2017-2023]",
            ),
        ),
        "United States" to listOf(
            LeagueModel(
                strSport = "Fighting",
                strLeague = "Game Changer Wrestling",
                strCurrentSeason = "2023",
                intFormedYear = "2008",
                strDescriptionEN = "Game Changer Wrestling (GCW) is a professional wrestling promotion based in New Jersey. The league originally closed in 2004, but started hosting events again in 2013 as the successor to Liberty States Wrestling .",
                strTvRights = "",
            ),
            LeagueModel(
                strSport = "Motorsport",
                strLeague = "IMSA Prototype Challenge Series",
                strCurrentSeason = "2023",
                intFormedYear = "2008",
                strDescriptionEN = "IMSA Prototype Challenge presented by Mazda (formerly IMSA Prototype Lites, Cooper Tires Prototype Lites, Mazda Prototype Lites) is a racing series featuring two classes of single-seat prototype cars racing simultaneously. The series is sanctioned by the International Motor Sports Association (IMSA). Most races are held in support of the WeatherTech SportsCar Championship. The L1 class features Élan Motorsport Technologies DP02 carbon tub cars powered by a 2.0 L Mazda MZR engine. In 2013 the L2 class switched to the same Elan DP02 tub of the L1, but fitted with the car's previous generation 2.3 L Mazda engine. Each class has an overall championship, a master’s championship for drivers at least 40 years of age, and a team championship. Each race is usually 30–45 minutes.",
                strTvRights = "",
            ),
        ),
    )

    override suspend operator fun invoke(params: String): ApiResult<LeagueListModel> {
        delay(1000) // Simulate a delay in the API response for 1 second
        return when (params) {
            in leagueModels -> {
                val leagueListModel = LeagueListModel(countries = leagueModels[params]!!)
                ApiResult.Success(leagueListModel)
            }
            WRONG_COUNTRY_NAME -> {
                ApiResult.GenericError(
                    code = BAD_REQUEST_CODE,
                    errorMessage = BAD_REQUEST,
                )
            }
            FAKE_NETWORK_ERROR -> {
                ApiResult.NetworkError(errorMessage = NETWORK_ERROR_MESSAGE)
            }
            else -> {
                val leagueListModel = LeagueListModel(countries = listOf())
                // Create a fake ApiResult object with the leagueListModel
                ApiResult.Success(value = leagueListModel)
            }
        }
    }

    private companion object {
        const val BAD_REQUEST = "Bad Request"
        const val BAD_REQUEST_CODE = 400
        const val WRONG_COUNTRY_NAME = "xyz"
        const val FAKE_NETWORK_ERROR = "NetworkError"
        const val NETWORK_ERROR_MESSAGE = "Please check your internet connection"
    }
}
