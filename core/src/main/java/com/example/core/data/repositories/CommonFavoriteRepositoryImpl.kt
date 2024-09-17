package com.example.core.data.repositories


import android.util.Log
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
        Log.d("Add", "Click completed")
        dao.insertFavorite(VacancyEntity(id, !isFavorite))
        Log.d("Add", "Click result${dao.getFavourites()}")
        delay(500)
        Log.d("Add", "_____________")
    }

    override suspend fun getFavoritesFlow(): Flow<List<FavoriteVacancy>> {
        return dao.getFavouritesFlow().map { entities ->
            entities.map { entity ->
                mapper.entityToFavoriteVacancy(entity)
            }
        }
    }
}