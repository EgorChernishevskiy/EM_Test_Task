package com.example.core.presentation.navigation

import com.example.core.presentation.models.VacancyUI

interface Navigation {
    fun navigateToVacancy(vacancy: VacancyUI)
    fun navigateBack()
}