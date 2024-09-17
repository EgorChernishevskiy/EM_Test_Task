package com.example.main.presentation.mappers.vacancymapper

import com.example.core.domain.models.Vacancy
import com.example.core.presentation.models.VacancyUI

interface VacancyMapper {
    fun map(domainModel: Vacancy): VacancyUI
}