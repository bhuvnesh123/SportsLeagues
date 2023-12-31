package com.example.sports_data.repository.fakes

import com.example.common.UIText
import com.example.sports_data.service.SportsService
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSportsService : SportsService {
    private var apiError: ApiResult<Any>? = null

    override fun getAllCountries(dispatcher: CoroutineDispatcher): Flow<ApiResult<CountriesListModel>> {
        return apiError?.let {
            flow {
                emit(it as ApiResult<CountriesListModel>)
            }
        } ?: run {
            flow {
                emit(
                    ApiResult.Success(
                        value = getCountryList(),
                    ),
                )
            }
        }
    }

    override fun searchLeaguesByCountry(
        dispatcher: CoroutineDispatcher,
        countryName: String,
    ): Flow<ApiResult<LeagueListModel>> {
        return apiError?.let {
            flow {
                emit(
                    it as ApiResult<LeagueListModel>,
                )
            }
        } ?: run {
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
            code = BAD_REQUEST_CODE,
            errorMessage = UIText.DynamicString(
                input = BAD_REQUEST,
            ),
        ),
    ) {
        apiError = if (isError) apiResult else null
    }

    fun getCountryList() = CountriesListModel(
        countries = listOf(
            CountryModel(name = "Country 1"),
            CountryModel(name = "Country 2"),
            CountryModel(name = "Country 3"),
        ),
    )

    fun getLeagues() = LeagueListModel(
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

    private companion object {
        const val BAD_REQUEST = "Bad Request"
        const val BAD_REQUEST_CODE = 400
    }
}
