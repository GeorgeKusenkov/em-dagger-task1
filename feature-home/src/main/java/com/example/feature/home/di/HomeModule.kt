package com.example.feature.home.di

import com.example.core.data.network.breakingbadapi.BreakingBadApi
import com.example.core.data.network.catapi.CatApi
import com.example.feature.home.data.repository.BreakingBadRepository
import com.example.feature.home.data.repository.CatRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideBreakingBadRepository(api: BreakingBadApi): BreakingBadRepository {
        return BreakingBadRepository(api)
    }

    @Provides
    fun provideCatRepository(api: CatApi): CatRepository {
        return CatRepository(api)
    }

}