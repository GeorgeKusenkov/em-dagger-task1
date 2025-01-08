package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreakingBadQuotes (
    @SerialName("quote") val quote: String,
    @SerialName("author") val author: String,
)