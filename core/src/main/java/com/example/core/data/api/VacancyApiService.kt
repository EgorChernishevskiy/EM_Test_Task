package com.example.core.data.api

import com.example.core.data.models.responses.SearchResponse
import retrofit2.Response
import retrofit2.http.GET

interface VacancyApiService {
    @GET("vacancies")
    suspend fun getVacancies(): Response<SearchResponse>
}