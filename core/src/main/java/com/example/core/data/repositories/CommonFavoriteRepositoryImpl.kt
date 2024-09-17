package com.example.core.data.repositories

import com.example.core.data.database.dao.VacancyDao
import com.example.core.data.database.entity.VacancyEntity
import com.example.core.data.mappres.favoritevacancymapper.IFavoriteVacancyMapper
import com.example.core.domain.models.FavoriteVacancy
import com.example.core.domain.repositories.ICommonFavoriteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommonFavoriteRepositoryImpl(
    private val dao: VacancyDao,
    private val mapper: IFavoriteVacancyMapper
) : ICommonFavoriteRepository {
    override suspend fun insertFavorite(id: String) {
        val isFavorite = dao.getFavouriteById(id) != null
        dao.insertFavorite(VacancyEntity(id, !isFavorite))
        delay(500)
    }

    override suspend fun getFavoritesFlow(): Flow<List<FavoriteVacancy>> {
        return dao.getFavouritesFlow().map { entities ->
            entities.map { entity ->
                mapper.entityToFavoriteVacancy(entity)
            }
        }
    }
}