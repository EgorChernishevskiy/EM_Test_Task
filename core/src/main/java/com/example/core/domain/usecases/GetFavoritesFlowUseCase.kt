package com.example.core.domain.usecases

import com.example.core.domain.models.FavoriteVacancy
import com.example.core.domain.repositories.ICommonFavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesFlowUseCase(private val repository: ICommonFavoriteRepository) {
    suspend fun execute(): Flow<List<FavoriteVacancy>> {
        return repository.getFavoritesFlow()
    }
}