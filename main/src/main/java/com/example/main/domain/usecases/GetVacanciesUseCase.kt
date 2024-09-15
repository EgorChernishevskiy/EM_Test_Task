package com.example.main.domain.usecases

import com.example.main.domain.models.Vacancy
import com.example.main.domain.repositories.ISearchRepository

class GetVacanciesUseCase(private val repository: ISearchRepository) {
    fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}