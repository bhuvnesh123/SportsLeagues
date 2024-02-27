package com.example.sports_data.di

import com.example.sports_data.repository.SportsRepositoryImpl
import com.example.sports_data.service.SportsService
import com.example.sports_data.service.SportsServiceImpl
import com.example.sports_domain.repository.SportsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindSportsService(sportsServiceImpl: SportsServiceImpl): SportsService

    @Binds
    abstract fun bindSportsRepository(sportsRepositoryImpl: SportsRepositoryImpl): SportsRepository
}
