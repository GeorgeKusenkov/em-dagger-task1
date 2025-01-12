package com.example.feature.home.di

import com.example.feature.home.data.repository.BreakingBadRepositoryImpl
import com.example.feature.home.data.repository.CatRepositoryImpl
import com.example.feature.home.domain.BreakingBadRepository
import com.example.feature.home.domain.CatRepository
import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

    @Binds
    abstract fun bindBreakingBadRepository(impl: BreakingBadRepositoryImpl): BreakingBadRepository

    @Binds
    abstract fun bindCatsRepository(impl: CatRepositoryImpl): CatRepository
}