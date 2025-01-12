package com.example.feature.home.domain

import javax.inject.Inject

class GetRandomCatUrlUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): Result<String> {
        return repository.getCatImageUrl()
    }
}