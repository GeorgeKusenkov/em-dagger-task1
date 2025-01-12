package com.example.feature.home.domain

interface BreakingBadRepository {
    suspend fun getRandomQuote(): Result<String>
}