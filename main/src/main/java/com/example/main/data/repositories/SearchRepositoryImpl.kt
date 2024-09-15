package com.example.main.data.repositories

import com.example.main.data.api.ApiService
import com.example.main.data.mappres.offermapper.OfferMapper
import com.example.main.data.mappres.vacancymapper.VacancyMapper
import com.example.main.domain.models.Offer
import com.example.main.domain.models.Vacancy
import com.example.main.domain.repositories.ISearchRepository

class SearchRepositoryImpl(
    private val apiService: ApiService,
    private val offerMapper: OfferMapper,
    private val vacancyMapper: VacancyMapper
) : ISearchRepository {

    override fun getOffers(): List<Offer> {
        val response = apiService.getOffers().execute()
        return response.body()?.map { offerMapper.mapOfferDTOToOffer(it) } ?: emptyList()
    }

    override fun getVacancies(): List<Vacancy> {
        val response = apiService.getVacancies().execute()
        return response.body()?.map { vacancyMapper.mapVacancyDTOToVacancy(it) } ?: emptyList()
    }

    override fun getVacancyById(id: String): Vacancy? {
        val response = apiService.getVacancyById(id).execute()
        return response.body()?.let { vacancyMapper.mapVacancyDTOToVacancy(it) }
    }
}