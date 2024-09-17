package com.example.core.presentation.mappres

import com.example.core.domain.models.FavoriteVacancy
import com.example.core.presentation.models.FavoriteVacancyUI

interface IFavoriteVacancyMapperUI {
    fun favoriteVacancyToUI(vacancy: FavoriteVacancy): FavoriteVacancyUI
}