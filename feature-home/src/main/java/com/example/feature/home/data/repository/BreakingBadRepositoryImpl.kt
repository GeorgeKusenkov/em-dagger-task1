package com.example.feature.home.data.repository

import com.example.core.data.network.breakingbadapi.BreakingBadApi
import com.example.core.data.network.di.BreakingBad
import com.example.feature.home.domain.BreakingBadRepository
import javax.inject.Inject

class BreakingBadRepositoryImpl @Inject constructor(
    @BreakingBad private val api: BreakingBadApi
): BreakingBadRepository {
    override suspend fun getRandomQuote(): Result<String> {
        return try {
            val result = api.randomQuote().toQuotesDomain()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
