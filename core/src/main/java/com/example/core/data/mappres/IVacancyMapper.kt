package com.example.core.data.mappres

import com.example.core.data.models.VacancyDTO
import com.example.core.domain.models.Vacancy

interface IVacancyMapper {
    fun mapVacancyDTOToVacancy(dto: VacancyDTO): Vacancy
}