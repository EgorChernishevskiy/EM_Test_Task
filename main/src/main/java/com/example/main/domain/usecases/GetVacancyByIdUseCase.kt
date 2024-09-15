package com.example.main.domain.usecases

import com.example.main.domain.models.Vacancy
import com.example.main.domain.repositories.ISearchRepository

class GetVacancyByIdUseCase(private val repository: ISearchRepository) {
    fun execute(id: String): Vacancy? {
        return repository.getVacancyById(id)
    }
}