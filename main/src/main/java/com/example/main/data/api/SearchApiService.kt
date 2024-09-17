package com.example.main.data.api

import com.example.core.data.models.responses.SearchResponse
import retrofit2.Response
import retrofit2.http.GET

interface SearchApiService {
    @GET("offers")
    suspend fun getOffers(): Response<SearchResponse>

    @GET("vacancies")
    suspend fun getVacancies(): Response<SearchResponse>
}