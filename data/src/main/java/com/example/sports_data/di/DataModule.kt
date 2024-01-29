package com.example.sports_data.di

import com.example.sports_data.repository.SportsRepositoryImpl
import com.example.sports_data.service.SportsService
import com.example.sports_data.service.SportsServiceImpl
import com.example.sports_domain.repository.SportsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {
    companion object {
        @Provides
        fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    @Binds
    abstract fun bindSportsService(sportsServiceImpl: SportsServiceImpl): SportsService

    @Binds
    abstract fun bindSportsRepository(sportsRepositoryImpl: SportsRepositoryImpl): SportsRepository
}
