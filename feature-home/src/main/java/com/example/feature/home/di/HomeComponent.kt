package com.example.feature.home.di

import com.example.core.data.network.di.NetworkComponent
import com.example.feature.home.presentation.HomeViewModel
import dagger.Component

@Component(modules = [HomeModule::class], dependencies = [NetworkComponent::class])
interface HomeComponent {
    fun viewModel(): HomeViewModel

    @Component.Factory
    interface Factory {
        fun create(networkComponent: NetworkComponent): HomeComponent
    }
}