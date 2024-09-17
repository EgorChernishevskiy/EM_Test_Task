package com.example.core.presentation.mappres.favoritevacancymapper

import com.example.core.domain.models.FavoriteVacancy
import com.example.core.presentation.models.FavoriteVacancyUI

class FavoriteVacancyMapperUIImpl : IFavoriteVacancyMapperUI {
    override fun favoriteVacancyToUI(vacancy: FavoriteVacancy): FavoriteVacancyUI {
        return FavoriteVacancyUI(
            id = vacancy.id,
            isFavorite = vacancy.isFavorite
        )
    }
}