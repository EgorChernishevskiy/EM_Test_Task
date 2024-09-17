package com.example.core.domain.usecases

import com.example.core.domain.models.Vacancy
import com.example.core.domain.repositories.ICommonVacanciesRepository

class GetVacanciesUseCase(private val repository: ICommonVacanciesRepository) {
    suspend fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}