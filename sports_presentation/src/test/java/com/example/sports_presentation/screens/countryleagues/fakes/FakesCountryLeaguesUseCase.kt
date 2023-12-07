package com.example.sports_presentation.screens.countryleagues.fakes

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.CountryLeaguesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakesCountryLeaguesUseCase : CountryLeaguesUseCase {
    override fun invoke(countryName: String): Flow<ApiResult<LeagueListModel?>> =
        when (countryName) {
            "India" -> {
                val leagueModel = LeagueModel(
                    idLeague = "4460",
                    idSoccerXML = "",
                    idAPIfootball = "",
                    strSport = "Cricket",
                    strLeague = "Indian Premier League",
                    strLeagueAlternate = "IPL",
                    intDivision = "0",
                    idCup = "0",
                    strCurrentSeason = "2023",
                    intFormedYear = "2008",
                    dateFirstEvent = "2010-03-12",
                    strGender = "Male",
                    strCountry = "India",
                    strWebsite = "www.iplt20.com",
                    strFacebook = "www.facebook.com/IPL/",
                    strInstagram = "",
                    strTwitter = "twitter.com/ipl",
                    strYoutube = "www.youtube.com/ipl",
                    strRSS = "http://www.espncricinfo.com/rss/content/story/feeds/6.xml",
                    strDescriptionEN = "The Indian Premier League (IPL) is a professional Twenty20 cricket league in India contested during April and May of every year by franchise teams representing Indian cities. The league was founded by the Board of Control for Cricket in India (BCCI) in 2007. The title sponsor of the IPL in 2016 was Vivo Electronics, thus the league is officially known as the Vivo Indian Premier League. The current IPL title holders are Sunrisers Hyderabad.\r\n\r\nThe IPL is the most-attended cricket league in the world and ranks sixth among all sports leagues. In 2010, the IPL became the first sporting event in the world to be broadcast live on YouTube. The brand value of IPL was estimated to be US$4.5 billion in 2015 by American Appraisal, A Division of Duff & Phelps. According to BCCI, the 2015 IPL season contributed ₹11.5 billion (US$182 million) to the GDP of the Indian economy.\r\n\r\nAccording to global valuation and corporate finance advisor Duff & Phelps, the value of brand IPL has jumped to $4.16 billion after the 2016 edition, against $3.54 billion in 2015. The 19% jump is despite the fact that the US dollar to Indian rupee currency has depreciated by nearly 10%.",
                    strDescriptionDE = "",
                    strDescriptionFR = "",
                    strDescriptionIT = "",
                    strDescriptionCN = "",
                    strDescriptionJP = "",
                    strDescriptionRU = "",
                    strDescriptionES = "",
                    strDescriptionPT = "",
                    strDescriptionSE = "",
                    strDescriptionNL = "",
                    strDescriptionHU = "",
                    strDescriptionNO = "",
                    strDescriptionPL = "",
                    strDescriptionIL = "",
                    strTvRights = "US (Linear) - Willow [2017-2021]\r\nUS (Streaming) - ESPN+ [2021-2022]\r\nZA (Streaming) - SuperSport [2017-2023]\r\nUK (Streaming) - Sky Sports [2017-2023]",
                    strFanart1 = "https://www.thesportsdb.com/images/media/league/fanart/v3ckyf1544174408.jpg",
                    strFanart2 = "https://www.thesportsdb.com/images/media/league/fanart/ehstd91544174436.jpg",
                    strFanart3 = "https://www.thesportsdb.com/images/media/league/fanart/12vdq51544174546.jpg",
                    strFanart4 = "https://www.thesportsdb.com/images/media/league/fanart/o8iunk1544174588.jpg",
                    strBanner = "https://www.thesportsdb.com/images/media/league/banner/kvzios1546529999.jpg",
                    strBadge = "https://www.thesportsdb.com/images/media/league/badge/9cm2uv1551037854.png",
                    strLogo = "https://www.thesportsdb.com/images/media/league/logo/kvmd941551037675.png",
                    strPoster = "https://www.thesportsdb.com/images/media/league/poster/506o8k1680292874.jpg",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/n40cna1684417361.png",
                    strNaming = "{strHomeTeam} vs {strAwayTeam}",
                    strComplete = "yes",
                    strLocked = "unlocked"
                )
                val leagueListModel = LeagueListModel(countries = listOf(leagueModel))
                // Create a fake ApiResult object with the leagueListModel
                val apiResult = ApiResult.Success(value = leagueListModel)

                // Create a fake Flow object that emits the fake ApiResult
                flow {
                    emit(value = apiResult)
                }

            }
            "United States" -> {
                val leagueModel = LeagueModel(
                    idLeague = "4867",
                    idSoccerXML = "",
                    idAPIfootball = "",
                    strSport = "Fighting",
                    strLeague = "Game Changer Wrestling",
                    strLeagueAlternate = "IPL",
                    intDivision = "0",
                    idCup = "0",
                    strCurrentSeason = "2023",
                    intFormedYear = "2008",
                    dateFirstEvent = "2010-03-12",
                    strGender = "Male",
                    strCountry = "United States",
                    strWebsite = "",
                    strFacebook = "",
                    strInstagram = "",
                    strTwitter = "twitter.com/ipl",
                    strYoutube = "www.youtube.com/ipl",
                    strRSS = "http://www.espncricinfo.com/rss/content/story/feeds/6.xml",
                    strDescriptionEN = "Game Changer Wrestling (GCW) is a professional wrestling promotion based in New Jersey. The league originally closed in 2004, but started hosting events again in 2013 as the successor to Liberty States Wrestling .",
                    strDescriptionDE = "",
                    strDescriptionFR = "",
                    strDescriptionIT = "",
                    strDescriptionCN = "",
                    strDescriptionJP = "",
                    strDescriptionRU = "",
                    strDescriptionES = "",
                    strDescriptionPT = "",
                    strDescriptionSE = "",
                    strDescriptionNL = "",
                    strDescriptionHU = "",
                    strDescriptionNO = "",
                    strDescriptionPL = "",
                    strDescriptionIL = "",
                    strTvRights = "",
                    strFanart1 = "https://www.thesportsdb.com/images/media/league/fanart/v3ckyf1544174408.jpg",
                    strFanart2 = "https://www.thesportsdb.com/images/media/league/fanart/ehstd91544174436.jpg",
                    strFanart3 = "https://www.thesportsdb.com/images/media/league/fanart/12vdq51544174546.jpg",
                    strFanart4 = "https://www.thesportsdb.com/images/media/league/fanart/o8iunk1544174588.jpg",
                    strBanner = "https://www.thesportsdb.com/images/media/league/banner/kvzios1546529999.jpg",
                    strBadge = "https://www.thesportsdb.com/images/media/league/badge/9cm2uv1551037854.png",
                    strLogo = "https://www.thesportsdb.com/images/media/league/logo/kvmd941551037675.png",
                    strPoster = "https://www.thesportsdb.com/images/media/league/poster/506o8k1680292874.jpg",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/n40cna1684417361.png",
                    strNaming = "{strHomeTeam} vs {strAwayTeam}",
                    strComplete = "yes",
                    strLocked = "unlocked"
                )
                val leagueModel2 = LeagueModel(
                    idLeague = "4868",
                    idSoccerXML = "",
                    idAPIfootball = "",
                    strSport = "Motorsport",
                    strLeague = "IMSA Prototype Challenge Series",
                    strLeagueAlternate = "IPL",
                    intDivision = "0",
                    idCup = "0",
                    strCurrentSeason = "2023",
                    intFormedYear = "2008",
                    dateFirstEvent = "2010-03-12",
                    strGender = "Male",
                    strCountry = "United States",
                    strWebsite = "www.iplt20.com",
                    strFacebook = "www.facebook.com/IPL/",
                    strInstagram = "",
                    strTwitter = "twitter.com/ipl",
                    strYoutube = "www.youtube.com/ipl",
                    strRSS = "http://www.espncricinfo.com/rss/content/story/feeds/6.xml",
                    strDescriptionEN = "IMSA Prototype Challenge presented by Mazda (formerly IMSA Prototype Lites, Cooper Tires Prototype Lites, Mazda Prototype Lites) is a racing series featuring two classes of single-seat prototype cars racing simultaneously. The series is sanctioned by the International Motor Sports Association (IMSA). Most races are held in support of the WeatherTech SportsCar Championship. The L1 class features Élan Motorsport Technologies DP02 carbon tub cars powered by a 2.0 L Mazda MZR engine. In 2013 the L2 class switched to the same Elan DP02 tub of the L1, but fitted with the car's previous generation 2.3 L Mazda engine. Each class has an overall championship, a master’s championship for drivers at least 40 years of age, and a team championship. Each race is usually 30–45 minutes.",
                    strDescriptionDE = "",
                    strDescriptionFR = "",
                    strDescriptionIT = "",
                    strDescriptionCN = "",
                    strDescriptionJP = "",
                    strDescriptionRU = "",
                    strDescriptionES = "",
                    strDescriptionPT = "",
                    strDescriptionSE = "",
                    strDescriptionNL = "",
                    strDescriptionHU = "",
                    strDescriptionNO = "",
                    strDescriptionPL = "",
                    strDescriptionIL = "",
                    strTvRights = "",
                    strFanart1 = "https://www.thesportsdb.com/images/media/league/fanart/v3ckyf1544174408.jpg",
                    strFanart2 = "https://www.thesportsdb.com/images/media/league/fanart/ehstd91544174436.jpg",
                    strFanart3 = "https://www.thesportsdb.com/images/media/league/fanart/12vdq51544174546.jpg",
                    strFanart4 = "https://www.thesportsdb.com/images/media/league/fanart/o8iunk1544174588.jpg",
                    strBanner = "https://www.thesportsdb.com/images/media/league/banner/kvzios1546529999.jpg",
                    strBadge = "https://www.thesportsdb.com/images/media/league/badge/9cm2uv1551037854.png",
                    strLogo = "https://www.thesportsdb.com/images/media/league/logo/kvmd941551037675.png",
                    strPoster = "https://www.thesportsdb.com/images/media/league/poster/506o8k1680292874.jpg",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/n40cna1684417361.png",
                    strNaming = "{strHomeTeam} vs {strAwayTeam}",
                    strComplete = "yes",
                    strLocked = "unlocked"
                )
                val leagueListModel = LeagueListModel(countries = listOf(leagueModel, leagueModel2))
                // Create a fake ApiResult object with the leagueListModel
                val apiResult = ApiResult.Success(value = leagueListModel)

                // Create a fake Flow object that emits the fake ApiResult
                flow {
                    emit(value = apiResult)
                }

            }
            else -> {
                val leagueListModel = LeagueListModel(countries = listOf())
                // Create a fake ApiResult object with the leagueListModel
                val apiResult = ApiResult.Success(value = leagueListModel)

                // Create a fake Flow object that emits the fake ApiResult
                flow {
                    emit(value = apiResult)
                }
            }


        }

}