package com.example.main.data.mappres.vacancymapper

import com.example.main.data.models.dto.VacancyDTO
import com.example.main.domain.models.Vacancy

interface IVacancyMapper {
    fun mapVacancyDTOToVacancy(dto: VacancyDTO): Vacancy
}