package com.example.core.presentation.mappres.favoritevacancymapper

import com.example.core.domain.models.FavoriteVacancy
import com.example.core.presentation.models.FavoriteVacancyUI

interface IFavoriteVacancyMapperUI {
    fun favoriteVacancyToUI(vacancy: FavoriteVacancy): FavoriteVacancyUI
}