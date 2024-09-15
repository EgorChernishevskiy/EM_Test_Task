package com.example.main.data.mappres.vacancymapper

import com.example.main.data.models.VacancyDTO
import com.example.main.domain.models.Vacancy

interface VacancyMapper {
    fun mapVacancyDTOToVacancy(dto: VacancyDTO): Vacancy
}