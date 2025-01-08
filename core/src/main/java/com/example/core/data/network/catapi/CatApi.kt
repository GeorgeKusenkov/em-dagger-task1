package com.example.core.data.network.catapi

import com.example.core.data.model.Cat
import retrofit2.http.GET

interface CatApi {

    @GET("v1/images/search")
    suspend fun randomCat(): List<Cat>
}