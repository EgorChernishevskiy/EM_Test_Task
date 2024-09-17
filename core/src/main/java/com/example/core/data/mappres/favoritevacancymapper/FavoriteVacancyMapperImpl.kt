package com.example.core.data.mappres.favoritevacancymapper

import com.example.core.data.database.entity.VacancyEntity
import com.example.core.domain.models.FavoriteVacancy

class FavoriteVacancyMapperImpl : IFavoriteVacancyMapper {
    override fun entityToFavoriteVacancy(entity: VacancyEntity): FavoriteVacancy {
        return FavoriteVacancy(
            id = entity.id,
            isFavorite = entity.isFavorite
        )
    }
}