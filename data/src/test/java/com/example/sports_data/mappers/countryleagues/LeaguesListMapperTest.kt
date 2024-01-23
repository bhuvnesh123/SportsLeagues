package com.example.sports_data.mappers.countryleagues

import com.example.sports_data.mappers.fakes.ResponseDTOFake
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LeaguesListMapperTest {
    private lateinit var leaguesListMapper: LeaguesListMapper

    @BeforeEach
    fun setup() {
        leaguesListMapper = LeaguesListMapper(leaguesMapper = LeaguesMapper())
    }

    @Test
    fun `GIVEN list of leagues dto as input WHEN mapper method called THEN return converted domain model list`() {
        val input = ResponseDTOFake.getLeagueResponseDTO()
        val expectedOutput = LeagueListModel(
            countries = listOf(
                LeagueModel(
                    strSport = "Football",
                    strLeague = "League 1",
                    strCurrentSeason = "2021-2022",
                    intFormedYear = "2000",
                    strDescriptionEN = "English description of League 1",
                    strTvRights = "TV rights for League 1",
                ),
                LeagueModel(
                    strSport = "Football",
                    strLeague = "League 2",
                    strCurrentSeason = "2021-2022",
                    intFormedYear = "2000",
                    strDescriptionEN = "English description of League 2",
                    strTvRights = "TV rights for League 2",
                ),
            ),
        )

        val result = leaguesListMapper.map(input = input)

        assertEquals(
            expectedOutput,
            result,
        )
    }
}
