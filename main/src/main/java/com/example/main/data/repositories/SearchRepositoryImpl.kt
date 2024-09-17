package com.example.main.data.repositories

import com.example.main.data.api.SearchApiService
import com.example.main.data.mappres.IOfferMapper
import com.example.core.data.mappres.vacancymapper.IVacancyMapper
import com.example.main.domain.models.Offer
import com.example.main.domain.models.VacanciesAmount
import com.example.core.domain.models.Vacancy
import com.example.main.domain.repositories.ISearchRepository

class SearchRepositoryImpl(
    private val searchApiService: SearchApiService,
    private val offerMapper: IOfferMapper
) : ISearchRepository {

    override suspend fun getOffers(): List<Offer> {
        return try {
            val response = searchApiService.getOffers()
            response.body()?.offers?.map { offerMapper.mapOfferDTOToOffer(it) } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getVacanciesAmount(): VacanciesAmount {
        return try {
            val response = searchApiService.getVacancies()
            val vacancies = response.body()?.vacancies ?: emptyList()
            VacanciesAmount(count = vacancies.size)
        } catch (e: Exception) {
            VacanciesAmount(count = 0)
        }
    }
}