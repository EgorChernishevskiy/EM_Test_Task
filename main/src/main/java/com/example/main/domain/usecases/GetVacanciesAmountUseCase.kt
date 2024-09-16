package com.example.main.domain.usecases

import com.example.main.domain.models.VacanciesAmount
import com.example.main.domain.repositories.ISearchRepository

class GetVacanciesCountUseCase(
    private val searchRepository: ISearchRepository
) {
    suspend fun execute(): VacanciesAmount {
        return searchRepository.getVacanciesAmount()
    }
}