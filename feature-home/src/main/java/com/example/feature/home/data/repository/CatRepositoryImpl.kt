package com.example.feature.home.data.repository

import com.example.core.data.network.catapi.CatApi
import com.example.core.data.network.di.Cats
import com.example.feature.home.domain.CatRepository
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    @Cats private val catApi: CatApi
): CatRepository {
    override suspend fun getCatImageUrl(): Result<String> {
        return try {
            val result = catApi.randomCat().toCatDomain()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
