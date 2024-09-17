package com.example.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyEntity(
    @PrimaryKey val id: String,
    val isFavorite: Boolean
)
