package com.example.main.domain.repositories

import com.example.main.domain.models.Offer
import com.example.main.domain.models.VacanciesAmount
import com.example.core.domain.models.Vacancy

interface ISearchRepository {
    suspend fun getOffers(): List<Offer>
    suspend fun getVacancies(): List<Vacancy>
    suspend fun getVacanciesAmount(): VacanciesAmount
}