package com.example.feature.home.domain

import com.example.feature.home.data.model.BadQuoteText
import com.example.feature.home.data.repository.BreakingBadRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: BreakingBadRepository
) {
    suspend operator fun invoke(): Result<BadQuoteText> {
        return repository.getRandomQuote().map { text -> BadQuoteText(text) }
    }
}