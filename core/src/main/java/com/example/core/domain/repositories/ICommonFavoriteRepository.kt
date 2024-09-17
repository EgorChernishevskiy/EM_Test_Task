package com.example.core.domain.repositories

import com.example.core.domain.models.FavoriteVacancy
import kotlinx.coroutines.flow.Flow

interface ICommonFavoriteRepository {
    suspend fun insertFavorite(id: String)
    suspend fun getFavoritesFlow(): Flow<List<FavoriteVacancy>>
}