package com.example.sports_data.mappers.countryleagues

import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel

class LeaguesMapper {
    fun map(input: LeagueDTO): LeagueModel = with(input) {
        LeagueModel(
            idLeague = idLeague.orEmpty(),
            idSoccerXML = idSoccerXML.orEmpty(),
            idAPIfootball = idAPIfootball.orEmpty(),
            strSport = strSport.orEmpty(),
            strLeague = strLeague.orEmpty(),
            strLeagueAlternate = strLeagueAlternate.orEmpty(),
            intDivision = intDivision.orEmpty(),
            idCup = idCup.orEmpty(),
            strCurrentSeason = strCurrentSeason.orEmpty(),
            intFormedYear = intFormedYear.orEmpty(),
            dateFirstEvent = dateFirstEvent.orEmpty(),
            strGender = strGender.orEmpty(),
            strCountry = strCountry.orEmpty(),
            strWebsite = strWebsite.orEmpty(),
            strFacebook = strFacebook.orEmpty(),
            strInstagram = strInstagram.orEmpty(),
            strTwitter = strTwitter.orEmpty(),
            strYoutube = strYoutube.orEmpty(),
            strRSS = strRSS.orEmpty(),
            strDescriptionEN = strDescriptionEN.orEmpty(),
            strDescriptionDE = strDescriptionDE.orEmpty(),
            strDescriptionFR = strDescriptionFR.orEmpty(),
            strDescriptionIT = strDescriptionIT.orEmpty(),
            strDescriptionCN = strDescriptionCN.orEmpty(),
            strDescriptionJP = strDescriptionJP.orEmpty(),
            strDescriptionRU = strDescriptionRU.orEmpty(),
            strDescriptionES = strDescriptionES.orEmpty(),
            strDescriptionPT = strDescriptionPT.orEmpty(),
            strDescriptionSE = strDescriptionSE.orEmpty(),
            strDescriptionNL = strDescriptionNL.orEmpty(),
            strDescriptionHU = strDescriptionHU.orEmpty(),
            strDescriptionNO = strDescriptionNO.orEmpty(),
            strDescriptionPL = strDescriptionPL.orEmpty(),
            strDescriptionIL = strDescriptionIL.orEmpty(),
            strTvRights = strTvRights.orEmpty(),
            strFanart1 = strFanart1.orEmpty(), strFanart2 = strFanart2.orEmpty(),
            strFanart3 = strFanart3.orEmpty(),
            strFanart4 = strFanart4.orEmpty(),
            strBanner = strBanner.orEmpty(),
            strBadge = strBadge.orEmpty(),
            strLogo = strLogo.orEmpty(),
            strPoster = strPoster.orEmpty(),
            strTrophy = strTrophy.orEmpty(),
            strNaming = strNaming.orEmpty(),
            strComplete = strComplete.orEmpty(),
            strLocked = strLocked.orEmpty()
        )
    }


}