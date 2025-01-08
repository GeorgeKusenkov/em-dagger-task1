package com.example.feature.home.domain

import com.example.feature.home.data.model.CatImageUrl
import com.example.feature.home.data.repository.CatRepository
import javax.inject.Inject

class GetRandomCatUrlUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): Result<CatImageUrl> {
        return repository.getCatImageUrl().map { url -> CatImageUrl(url) }
    }
}