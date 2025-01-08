package com.example.core.data.network.di

import com.example.core.data.network.breakingbadapi.BreakingBadApi
import com.example.core.data.network.catapi.CatApi
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier


@Module
class NetworkModule {
    private val contentType = "application/json".toMediaType()
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @BreakingBad
    fun provideBreakingBadRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.breakingbadquotes.xyz/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Cats
    fun provideCatsRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    fun provideBreakingBadApi(@BreakingBad retrofit: Retrofit): BreakingBadApi {
        return retrofit.create(BreakingBadApi::class.java)
    }

    @Provides
    fun provideCatApi(@Cats retrofit: Retrofit): CatApi {
        return retrofit.create(CatApi::class.java)
    }
}

@Qualifier
annotation class BreakingBad

@Qualifier
annotation class Cats
