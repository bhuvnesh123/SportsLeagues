package com.example.sports_data.repository.fakes

import com.example.common.UIText
import com.example.sports_data.service.SportsService
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSportsService : SportsService {
    private var shouldEmitError = false
    private lateinit var apiError: ApiResult<Any>

    override fun getAllCountries(): Flow<ApiResult<CountriesListModel>> {
        return if (shouldEmitError) {
            flow {
                emit(
                    apiError as ApiResult<CountriesListModel>,
                )
            }
        } else {
            flow {
                emit(
                    ApiResult.Success(
                        value = getCountryList(),
                    ),
                )
            }
        }
    }

    override fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel>> {
        return if (shouldEmitError) {
            flow {
                emit(
                    apiError as ApiResult<LeagueListModel>,
                )
            }
        } else {
            flow {
                emit(
                    ApiResult.Success(
                        value = getLeagues(),
                    ),
                )
            }
        }
    }

    fun <T : Any> setShouldEmitError(
        isError: Boolean,
        apiResult: ApiResult<T> = ApiResult.GenericError(
            code = 400,
            errorMessage = UIText.DynamicString(
                input = BAD_REQUEST,
            ),
        ),
    ) {
        shouldEmitError = isError
        apiError = apiResult
    }

    fun getCountryList() = CountriesListModel(
        countries = listOf(
            CountryModel(name_en = "Country 1"),
            CountryModel(name_en = "Country 2"),
            CountryModel(name_en = "Country 3"),
        ),
    )

    fun getLeagues() = LeagueListModel(
        countries = listOf(
            LeagueModel(
                idLeague = "1",
                idSoccerXML = "soccer_xml_1",
                idAPIfootball = "api_football_1",
                strSport = "Football",
                strLeague = "League 1",
                strLeagueAlternate = "Alternate League 1",
                intDivision = "Division 1",
                idCup = "cup_1",
                strCurrentSeason = "2021-2022",
                intFormedYear = "2000",
                dateFirstEvent = "2001-01-01",
                strGender = "Male",
                strCountry = "Country 1",
                strWebsite = "https://www.league1.com",
                strFacebook = "https://www.facebook.com/league1",
                strInstagram = "https://www.instagram.com/league1",
                strTwitter = "https://www.twitter.com/league1",
                strYoutube = "https://www.youtube.com/league1",
                strRSS = "https://www.league1.com/rss",
                strDescriptionEN = "English description of League 1",
                strDescriptionDE = "German description of League 1",
                strDescriptionFR = "French description of League 1",
                strDescriptionIT = "Italian description of League 1",
                strDescriptionCN = "Chinese description of League 1",
                strDescriptionJP = "Japanese description of League 1",
                strDescriptionRU = "Russian description of League 1",
                strDescriptionES = "Spanish description of League 1",
                strDescriptionPT = "Portuguese description of League 1",
                strDescriptionSE = "Swedish description of League 1",
                strDescriptionNL = "Dutch description of League 1",
                strDescriptionHU = "Hungarian description of League 1",
                strDescriptionNO = "Norwegian description of League 1",
                strDescriptionPL = "Polish description of League 1",
                strDescriptionIL = "Hebrew description of League 1",
                strTvRights = "TV rights for League 1",
                strFanart1 = "https://www.league1.com/fanart1",
                strFanart2 = "https://www.league1.com/fanart2",
                strFanart3 = "https://www.league1.com/fanart3",
                strFanart4 = "https://www.league1.com/fanart4",
                strBanner = "https://www.league1.com/banner",
                strBadge = "https://www.league1.com/badge",
                strLogo = "https://www.league1.com/logo",
                strPoster = "https://www.league1.com/poster",
                strTrophy = "https://www.league1.com/trophy",
                strNaming = "Naming of League 1",
                strComplete = "Complete information about League 1",
                strLocked = "Locked status of League 1",
            ),
            LeagueModel(
                idLeague = "2",
                idSoccerXML = "soccer_xml_2",
                idAPIfootball = "api_football_2",
                strSport = "Football",
                strLeague = "League 2",
                strLeagueAlternate = "Alternate League 2",
                intDivision = "Division 2",
                idCup = "cup_2",
                strCurrentSeason = "2021-2022",
                intFormedYear = "2000",
                dateFirstEvent = "2001-01-01",
                strGender = "Male",
                strCountry = "Country 2",
                strWebsite = "https://www.league2.com",
                strFacebook = "https://www.facebook.com/league2",
                strInstagram = "https://www.instagram.com/league2",
                strTwitter = "https://www.twitter.com/league2",
                strYoutube = "https://www.youtube.com/league2",
                strRSS = "https://www.league2.com/rss",
                strDescriptionEN = "English description of League 2",
                strDescriptionDE = "German description of League 2",
                strDescriptionFR = "French description of League 2",
                strDescriptionIT = "Italian description of League2",
                strDescriptionCN = "Chinese description of League 2",
                strDescriptionJP = "Japanese description of League 2",
                strDescriptionRU = "Russian description of League 2",
                strDescriptionES = "Spanish description of League 2",
                strDescriptionPT = "Portuguese description of League 2",
                strDescriptionSE = "Swedish description of League 2",
                strDescriptionNL = "Dutch description of League 2",
                strDescriptionHU = "Hungarian description of League 2",
                strDescriptionNO = "Norwegian description of League 2",
                strDescriptionPL = "Polish description of League 2",
                strDescriptionIL = "Hebrew description of League 2",
                strTvRights = "TV rights for League 2",
                strFanart1 = "https://www.league2.com/fanart1",
                strFanart2 = "https://www.league2.com/fanart2",
                strFanart3 = "https://www.league2.com/fanart3",
                strFanart4 = "https://www.league2.com/fanart4",
                strBanner = "https://www.league2.com/banner",
                strBadge = "https://www.league2.com/badge",
                strLogo = "https://www.league2.com/logo",
                strPoster = "https://www.league2.com/poster",
                strTrophy = "https://www.league2.com/trophy",
                strNaming = "Naming of League 2",
                strComplete = "Complete information about League 2",
                strLocked = "Locked status of League 2",
            ),
        ),
    )

    private companion object {
        const val BAD_REQUEST = "Bad Request"
    }
}
