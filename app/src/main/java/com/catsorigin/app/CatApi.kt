package com.plcoding.retrofitcrashcourse

import retrofit2.Response
import retrofit2.http.GET

interface CatApi {

    @GET("/v1/breeds")
    suspend fun getCats(): Response<List<Cat>>

}