package com.example.core.data.mappres.vacancymapper

import com.example.core.data.models.dto.VacancyDTO
import com.example.core.domain.models.Vacancy

interface IVacancyMapper {
    fun mapVacancyDTOToVacancy(dto: VacancyDTO): Vacancy
}