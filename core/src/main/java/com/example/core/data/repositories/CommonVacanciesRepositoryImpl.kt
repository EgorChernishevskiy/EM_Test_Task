package com.example.core.data.repositories

import com.example.core.data.api.VacancyApiService
import com.example.core.data.mappres.vacancymapper.IVacancyMapper
import com.example.core.domain.models.Vacancy
import com.example.core.domain.repositories.ICommonVacanciesRepository

class CommonVacanciesRepositoryImpl(
    private val searchApiService: VacancyApiService,
    private val vacancyMapper: IVacancyMapper
) : ICommonVacanciesRepository {
    override suspend fun getVacancies(): List<Vacancy> {
        return try {
            val response = searchApiService.getVacancies()
            response.body()?.vacancies?.map { vacancyMapper.mapVacancyDTOToVacancy(it) } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}