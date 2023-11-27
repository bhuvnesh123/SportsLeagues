package com.example.sports_presentation.di

import com.example.sports_presentation.mappers.allcountries.CountryPresentationListMapper
import com.example.sports_presentation.mappers.allcountries.CountryPresentationMapper
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import com.example.sports_presentation.mappers.countryleagues.LeaguesPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PresentationModule {

    @Singleton
    @Provides
    fun provideCountryPresentationMapper(): CountryPresentationMapper {
        return CountryPresentationMapper()
    }

    @Singleton
    @Provides
    fun provideCountryListPresentationMapper(countryPresentationMapper: CountryPresentationMapper): CountryPresentationListMapper {
        return CountryPresentationListMapper(countryPresentationMapper = countryPresentationMapper)
    }

    @Singleton
    @Provides
    fun provideLeaguesPresentationMapper(): LeaguesPresentationMapper {
        return LeaguesPresentationMapper()
    }

    @Singleton
    @Provides
    fun provideLeaguesListPresentationMapper(leaguesPresentationMapper: LeaguesPresentationMapper): LeaguesListPresentationMapper {
        return LeaguesListPresentationMapper(leaguesPresentationMapper = leaguesPresentationMapper)
    }
}