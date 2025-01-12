package com.example.feature.home.domain

import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: BreakingBadRepository
) {
    suspend operator fun invoke(): Result<String> {
        return repository.getRandomQuote()
    }
}