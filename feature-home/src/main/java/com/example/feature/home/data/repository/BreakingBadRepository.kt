package com.example.feature.home.data.repository

import com.example.core.data.network.breakingbadapi.BreakingBadApi

class BreakingBadRepository (private val api: BreakingBadApi) {
    suspend fun getRandomQuote(): Result<String> {
        return try {
            val result = api.randomQuote().toQuotesDomain()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
