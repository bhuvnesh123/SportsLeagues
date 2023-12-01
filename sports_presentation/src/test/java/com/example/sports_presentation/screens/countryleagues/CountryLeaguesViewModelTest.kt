package com.example.sports_presentation.screens.countryleagues

import com.example.sports_presentation.common.MainCoroutineRule
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import com.example.sports_presentation.mappers.countryleagues.LeaguesPresentationMapper
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel
import com.example.sports_presentation.screens.countryleagues.fakes.FakesCountryLeaguesUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MainCoroutineRule::class)
internal class CountryLeaguesViewModelTest {
    private lateinit var countryLeaguesViewModel: CountryLeaguesViewModel

    @BeforeEach
    fun setUp() {
        countryLeaguesViewModel = CountryLeaguesViewModel(
            countryLeaguesUseCase = FakesCountryLeaguesUseCase(),
            leaguesListPresentationMapper = LeaguesListPresentationMapper(
                LeaguesPresentationMapper()
            )
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["United States", "India", "Antartica"])
    fun `GIVEN query country WHEN LoadData ViewIntent sent THEN viewState contains list of leagues of that country`(input: String) {
        countryLeaguesViewModel.sendIntent(CountryLeaguesViewIntent.LoadData(input))

        expectedViewStateForInput(input = input)

    }

    private fun expectedViewStateForInput(input: String) {
        // Define the expected ViewState for each input parameter
        val expectedViewStateMap = mapOf(
            "United States" to CountryLeaguesViewState.Success(listOf(
                LeaguesPresentationModel(
                    leagueName = "Game Changer Wrestling",
                    sport = "Fighting",
                    leagueDescription = "Game Changer Wrestling (GCW) is a professional wrestling promotion based in New Jersey. The league originally closed in 2004, but started hosting events again in 2013 as the successor to Liberty States Wrestling .",
                    formedYear = "2008",
                    currentSeason = "2023",
                    tvRights = ""
                ),
                LeaguesPresentationModel(
                    leagueName = "IMSA Prototype Challenge Series",
                    sport = "Motorsport",
                    leagueDescription = "IMSA Prototype Challenge presented by Mazda (formerly IMSA Prototype Lites, Cooper Tires Prototype Lites, Mazda Prototype Lites) is a racing series featuring two classes of single-seat prototype cars racing simultaneously. The series is sanctioned by the International Motor Sports Association (IMSA). Most races are held in support of the WeatherTech SportsCar Championship. The L1 class features Élan Motorsport Technologies DP02 carbon tub cars powered by a 2.0 L Mazda MZR engine. In 2013 the L2 class switched to the same Elan DP02 tub of the L1, but fitted with the car's previous generation 2.3 L Mazda engine. Each class has an overall championship, a master’s championship for drivers at least 40 years of age, and a team championship. Each race is usually 30–45 minutes.",
                    currentSeason = "2023",
                    formedYear = "2008",
                    tvRights = ""
                )
            )),
            "India" to CountryLeaguesViewState.Success(listOf(
                LeaguesPresentationModel(
                    leagueName = "Indian Premier League",
                    sport = "Cricket",
                    leagueDescription = "The Indian Premier League (IPL) is a professional Twenty20 cricket league in India contested during April and May of every year by franchise teams representing Indian cities. The league was founded by the Board of Control for Cricket in India (BCCI) in 2007. The title sponsor of the IPL in 2016 was Vivo Electronics, thus the league is officially known as the Vivo Indian Premier League. The current IPL title holders are Sunrisers Hyderabad.\r\n\r\nThe IPL is the most-attended cricket league in the world and ranks sixth among all sports leagues. In 2010, the IPL became the first sporting event in the world to be broadcast live on YouTube. The brand value of IPL was estimated to be US$4.5 billion in 2015 by American Appraisal, A Division of Duff & Phelps. According to BCCI, the 2015 IPL season contributed ₹11.5 billion (US$182 million) to the GDP of the Indian economy.\r\n\r\nAccording to global valuation and corporate finance advisor Duff & Phelps, the value of brand IPL has jumped to $4.16 billion after the 2016 edition, against $3.54 billion in 2015. The 19% jump is despite the fact that the US dollar to Indian rupee currency has depreciated by nearly 10%.",
                    formedYear = "2008",
                    currentSeason = "2023",
                    tvRights = "US (Linear) - Willow [2017-2021]\r\nUS (Streaming) - ESPN+ [2021-2022]\r\nZA (Streaming) - SuperSport [2017-2023]\r\nUK (Streaming) - Sky Sports [2017-2023]",
                )
            )),
            "Antartica" to CountryLeaguesViewState.NoDataFound
        )
        val expectedViewState = expectedViewStateMap[input]
        assertEquals(expectedViewState, countryLeaguesViewModel.viewState.value)
    }
}