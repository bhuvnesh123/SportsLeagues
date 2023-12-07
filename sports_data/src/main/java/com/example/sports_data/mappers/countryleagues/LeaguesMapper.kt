package com.example.sports_data.mappers.countryleagues

import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel

class LeaguesMapper {
    fun map(input: LeagueDTO): LeagueModel = with(input) {
        LeagueModel(
            idLeague.orEmpty(),
            idSoccerXML.orEmpty(),
            idAPIfootball.orEmpty(),
            strSport.orEmpty(),
            strLeague.orEmpty(),
            strLeagueAlternate.orEmpty(),
            intDivision.orEmpty(),
            idCup.orEmpty(),
            strCurrentSeason.orEmpty(),
            intFormedYear.orEmpty(),
            dateFirstEvent.orEmpty(),
            strGender.orEmpty(),
            strCountry.orEmpty(),
            strWebsite.orEmpty(),
            strFacebook.orEmpty(),
            strInstagram.orEmpty(),
            strTwitter.orEmpty(),
            strYoutube.orEmpty(),
            strRSS.orEmpty(),
            strDescriptionEN.orEmpty(),
            strDescriptionDE.orEmpty(),
            strDescriptionFR.orEmpty(),
            strDescriptionIT.orEmpty(),
            strDescriptionCN.orEmpty(),
            strDescriptionJP.orEmpty(),
            strDescriptionRU.orEmpty(),
            strDescriptionES.orEmpty(),
            strDescriptionPT.orEmpty(),
            strDescriptionSE.orEmpty(),
            strDescriptionNL.orEmpty(),
            strDescriptionHU.orEmpty(),
            strDescriptionNO.orEmpty(),
            strDescriptionPL.orEmpty(),
            strDescriptionIL.orEmpty(),
            strTvRights.orEmpty(),
            strFanart1.orEmpty(), strFanart2.orEmpty(),
            strFanart3.orEmpty(),
            strFanart4.orEmpty(),
            strBanner.orEmpty(),
            strBadge.orEmpty(),
            strLogo.orEmpty(),
            strPoster.orEmpty(),
            strTrophy.orEmpty(),
            strNaming.orEmpty(),
            strComplete.orEmpty(),
            strLocked.orEmpty()
        )
    }


}