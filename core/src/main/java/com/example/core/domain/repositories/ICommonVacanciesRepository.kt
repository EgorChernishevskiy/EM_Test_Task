package com.example.core.domain.repositories

import com.example.core.domain.models.Vacancy

interface ICommonVacanciesRepository {
    suspend fun getVacancies(): List<Vacancy>
}