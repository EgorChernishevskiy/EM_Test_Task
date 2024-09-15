package com.example.main.domain.repositories

import com.example.main.domain.models.Offer
import com.example.main.domain.models.Vacancy

interface ISearchRepository {
    fun getOffers(): List<Offer>
    fun getVacancies(): List<Vacancy>
    fun getVacancyById(id: String): Vacancy?
}