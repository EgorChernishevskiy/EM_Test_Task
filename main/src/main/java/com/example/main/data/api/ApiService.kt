package com.example.main.data.api

import com.example.main.data.models.OfferDTO
import com.example.main.data.models.VacancyDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("offers")
    fun getOffers(): Call<List<OfferDTO>>

    @GET("vacancies")
    fun getVacancies(): Call<List<VacancyDTO>>

    @GET("vacancies/{id}")
    fun getVacancyById(@Path("id") id: String): Call<VacancyDTO>
}