package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat (
    @SerialName("id") val id: String,
    @SerialName("url") val url: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int
)