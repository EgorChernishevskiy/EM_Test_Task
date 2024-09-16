package com.example.main.data.api

import com.example.main.data.models.dto.VacancyDTO
import com.example.main.data.models.responses.SearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("offers")
    suspend fun getOffers(): Response<SearchResponse>

    @GET("vacancies")
    suspend fun getVacancies(): Response<SearchResponse>

    @GET("vacancies/{id}")
    suspend fun getVacancyById(@Path("id") id: String): Response<VacancyDTO>
}