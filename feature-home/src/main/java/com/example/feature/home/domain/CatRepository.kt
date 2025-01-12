package com.example.feature.home.domain

interface CatRepository {
    suspend fun getCatImageUrl(): Result<String>
}
