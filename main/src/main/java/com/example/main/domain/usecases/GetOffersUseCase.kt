package com.example.main.domain.usecases

import com.example.main.domain.models.Offer
import com.example.main.domain.repositories.ISearchRepository

class GetOffersUseCase(private val repository: ISearchRepository) {
    suspend fun execute(): List<Offer> {
        return repository.getOffers()
    }
}