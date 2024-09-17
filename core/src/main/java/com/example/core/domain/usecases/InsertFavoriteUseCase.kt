package com.example.core.domain.usecases

import com.example.core.domain.repositories.ICommonFavoriteRepository

class InsertFavoriteUseCase(private val repository: ICommonFavoriteRepository) {
    suspend fun execute(vacancyId: String) {
        repository.insertFavorite(vacancyId)
    }
}