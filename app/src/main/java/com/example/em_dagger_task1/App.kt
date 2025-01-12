package com.example.em_dagger_task1

import android.app.Application
import com.example.core.data.network.di.DaggerNetworkComponent
import com.example.core.data.network.di.NetworkComponent
import com.example.feature.home.di.DaggerHomeComponent
import com.example.feature.home.di.HomeComponent

class App : Application() {
    private lateinit var networkComponent: NetworkComponent
    lateinit var homeComponent: HomeComponent

    override fun onCreate() {
        super.onCreate()
        initializeNetworkComponent()
        initializeHomeComponent()
    }

    private fun initializeNetworkComponent() {
        networkComponent = DaggerNetworkComponent.factory().create()
    }

    private fun initializeHomeComponent() {
        homeComponent = DaggerHomeComponent.factory().create(networkComponent)
    }
}