package com.example.core.data.network.di

import com.example.core.data.network.breakingbadapi.BreakingBadApi
import com.example.core.data.network.catapi.CatApi
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    @BreakingBad
    fun breakingBadApi(): BreakingBadApi

    @Cats
    fun catApi(): CatApi

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponent
    }
}
