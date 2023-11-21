package com.example.sports_data.di

import com.example.sports_data.Constants.BASE_URL
import com.example.sports_data.api.SportsApi
import com.example.sports_data.mappers.CountriesListMapper
import com.example.sports_data.mappers.CountriesListMapperImpl
import com.example.sports_data.mappers.CountryMapper
import com.example.sports_data.mappers.CountryMapperImpl
import com.example.sports_data.mappers.LeaguesListMapper
import com.example.sports_data.mappers.LeaguesListMapperImpl
import com.example.sports_data.mappers.LeaguesMapper
import com.example.sports_data.mappers.LeaguesMapperImpl
import com.example.sports_data.repository.SportsRepositoryImpl
import com.example.sports_data.service.SportsService
import com.example.sports_data.service.SportsServiceImpl
import com.example.sports_domain.SportsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(SingletonComponent::class)
@Module
object SingletonModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesSportsApi(retrofit: Retrofit): SportsApi {
        return retrofit.create(SportsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCountriesMapper(): CountryMapper {
        return CountryMapperImpl()
    }
    @Singleton
    @Provides
    fun provideCountriesListMapper(countryMapper: CountryMapper): CountriesListMapper {
        return CountriesListMapperImpl(countryMapper = countryMapper)
    }
    @Singleton
    @Provides
    fun provideLeaguesMapper(): LeaguesMapper {
        return LeaguesMapperImpl()
    }
    @Singleton
    @Provides
    fun provideLeaguesListMapper(leaguesMapper: LeaguesMapper): LeaguesListMapper {
        return LeaguesListMapperImpl(leaguesMapper = leaguesMapper)
    }

    @Singleton
    @Provides
    fun provideSportsService(sportsApi: SportsApi, countriesListMapper: CountriesListMapper, leaguesListMapper: LeaguesListMapper) : SportsService {
        return SportsServiceImpl(sportsApi = sportsApi, countriesListMapperImpl = countriesListMapper, leaguesListMapperImpl = leaguesListMapper)
    }

    @Singleton
    @Provides
    fun providesSportsRepository(sportsService: SportsService): SportsRepository {
        return SportsRepositoryImpl(sportsService=sportsService)
    }


}