package com.example.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.database.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(newEntity: VacancyEntity)

    @Query("SELECT * FROM VacancyEntity WHERE isFavorite = 1")
    suspend fun getFavourites(): List<VacancyEntity>

    @Query("SELECT * FROM VacancyEntity WHERE isFavorite = 1")
    fun getFavouritesFlow(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM VacancyEntity WHERE id LIKE :id AND isFavorite = 1 LIMIT 1")
    suspend fun getFavouriteById(id: String): VacancyEntity?

}