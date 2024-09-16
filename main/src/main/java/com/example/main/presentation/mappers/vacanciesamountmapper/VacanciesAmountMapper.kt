package com.example.main.presentation.mappers.vacanciesamountmapper

import com.example.main.domain.models.VacanciesAmount
import com.example.main.presentation.models.VacanciesAmountUI

interface VacanciesAmountMapper {
    fun map(domainModel: VacanciesAmount): VacanciesAmountUI
}