package com.example.test.task.data.remote

import com.example.test.task.data.model.FactResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    @GET("{number}?json")
    suspend fun getFactForNumber(@Path("number") number: Int): FactResponse

    @GET("random/math?json")
    suspend fun getRandomFact(): FactResponse
}