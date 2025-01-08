package com.example.em_dagger_task1

import android.app.Application
import com.example.core.data.network.di.DaggerNetworkComponent
import com.example.core.data.network.di.NetworkComponent

class App : Application() {
    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        initializeNetworkComponent()
    }

    private fun initializeNetworkComponent() {
        networkComponent = DaggerNetworkComponent.factory().create()
    }
}