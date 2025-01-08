package com.example.feature.home.data.repository

import com.example.core.data.network.catapi.CatApi

class CatRepository (private val catApi: CatApi) {
    suspend fun getCatImageUrl(): Result<String> {
        return try {
            val result = catApi.randomCat().toCatDomain()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
