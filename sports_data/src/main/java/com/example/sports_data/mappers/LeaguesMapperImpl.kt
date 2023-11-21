package com.example.sports_data.mappers

import com.example.sports_data.dto.countryLeagues.League
import com.example.sports_domain.domainModels.countryLeagues.LeagueModel

class LeaguesMapperImpl : LeaguesMapper {
    override fun map(input: League): LeagueModel = with(input) {
        LeagueModel(
            idLeague,
            idSoccerXML,
            idAPIfootball,
            strSport,
            strLeague,
            strLeagueAlternate,
            intDivision,
            idCup,
            strCurrentSeason,
            intFormedYear,
            dateFirstEvent,
            strGender,
            strCountry,
            strWebsite,
            strFacebook,
            strInstagram,
            strTwitter,
            strYoutube,
            strRSS,
            strDescriptionEN,
            strDescriptionDE,
            strDescriptionFR,
            strDescriptionIT,
            strDescriptionCN,
            strDescriptionJP,
            strDescriptionRU,
            strDescriptionES,
            strDescriptionPT,
            strDescriptionSE,
            strDescriptionNL,
            strDescriptionHU,
            strDescriptionNO,
            strDescriptionPL,
            strDescriptionIL,
            strTvRights,
            strFanart1, strFanart2,
            strFanart3,
            strFanart4,
            strBanner,
            strBadge,
            strLogo,
            strPoster,
            strTrophy,
            strNaming,
            strComplete,
            strLocked
        )
    }


}