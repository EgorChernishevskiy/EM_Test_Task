package com.example.main.domain.usecases

import com.example.core.domain.models.Vacancy
import com.example.main.domain.repositories.ISearchRepository

class GetVacanciesUseCase(private val repository: ISearchRepository) {
    suspend fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}