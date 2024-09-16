package com.example.main.data.repositories

import com.example.main.data.api.ApiService
import com.example.main.data.mappres.offermapper.IOfferMapper
import com.example.main.data.mappres.vacancymapper.IVacancyMapper
import com.example.main.domain.models.Offer
import com.example.main.domain.models.VacanciesAmount
import com.example.main.domain.models.Vacancy
import com.example.main.domain.repositories.ISearchRepository

class SearchRepositoryImpl(
    private val apiService: ApiService,
    private val offerMapper: IOfferMapper,
    private val vacancyMapper: IVacancyMapper
) : ISearchRepository {

    override suspend fun getOffers(): List<Offer> {
        return try {
            val response = apiService.getOffers()
            response.body()?.offers?.map { offerMapper.mapOfferDTOToOffer(it) } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return try {
            val response = apiService.getVacancies()
            response.body()?.vacancies?.map { vacancyMapper.mapVacancyDTOToVacancy(it) } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getVacancyById(id: String): Vacancy? {
        return try {
            val response = apiService.getVacancyById(id)
            response.body()?.let { vacancyMapper.mapVacancyDTOToVacancy(it) }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getVacanciesAmount(): VacanciesAmount {
        return try {
            val response = apiService.getVacancies()
            val vacancies = response.body()?.vacancies ?: emptyList()
            VacanciesAmount(count = vacancies.size)
        } catch (e: Exception) {
            VacanciesAmount(count = 0)
        }
    }
}