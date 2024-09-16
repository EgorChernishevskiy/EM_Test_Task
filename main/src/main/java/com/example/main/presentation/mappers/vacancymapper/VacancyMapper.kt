package com.example.main.presentation.mappers.vacancymapper

import com.example.main.domain.models.Vacancy
import com.example.main.presentation.models.VacancyUI

interface VacancyMapper {
    fun map(domainModel: Vacancy): VacancyUI
}