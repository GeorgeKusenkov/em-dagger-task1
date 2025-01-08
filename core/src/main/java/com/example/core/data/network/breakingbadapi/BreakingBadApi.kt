package com.example.core.data.network.breakingbadapi

import com.example.core.data.model.BreakingBadQuotes
import retrofit2.http.GET

interface BreakingBadApi {

    @GET("v1/quotes")
    suspend fun randomQuote(): List<BreakingBadQuotes>
}

