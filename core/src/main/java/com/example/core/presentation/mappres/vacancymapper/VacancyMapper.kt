package com.example.core.presentation.mappres.vacancymapper

import com.example.core.domain.models.Vacancy
import com.example.core.presentation.models.VacancyUI

interface VacancyMapper {
    fun map(domainModel: Vacancy): VacancyUI
}