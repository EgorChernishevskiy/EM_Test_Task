package com.example.core.data.mappres.favoritevacancymapper

import com.example.core.data.database.entity.VacancyEntity
import com.example.core.domain.models.FavoriteVacancy

interface IFavoriteVacancyMapper {
    fun entityToFavoriteVacancy(entity: VacancyEntity): FavoriteVacancy
}