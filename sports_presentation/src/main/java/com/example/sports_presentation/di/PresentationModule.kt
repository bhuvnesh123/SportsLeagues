package com.example.sports_presentation.di

import com.example.sports_presentation.mappers.allcountries.CountryPresentationListMapper
import com.example.sports_presentation.mappers.allcountries.CountryPresentationMapper
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import com.example.sports_presentation.mappers.countryleagues.LeaguesPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object PresentationModule {

    @Provides
    fun provideCountryPresentationMapper(): CountryPresentationMapper = CountryPresentationMapper()


    @Provides
    fun provideCountryListPresentationMapper(countryPresentationMapper: CountryPresentationMapper): CountryPresentationListMapper =
        CountryPresentationListMapper(countryPresentationMapper = countryPresentationMapper)


    @Provides
    fun provideLeaguesPresentationMapper(): LeaguesPresentationMapper = LeaguesPresentationMapper()


    @Provides
    fun provideLeaguesListPresentationMapper(leaguesPresentationMapper: LeaguesPresentationMapper): LeaguesListPresentationMapper =
        LeaguesListPresentationMapper(leaguesPresentationMapper = leaguesPresentationMapper)

}