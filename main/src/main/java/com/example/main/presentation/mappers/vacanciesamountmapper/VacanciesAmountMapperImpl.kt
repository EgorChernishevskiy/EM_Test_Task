package com.example.main.presentation.mappers.vacanciesamountmapper

import com.example.main.domain.models.VacanciesAmount
import com.example.main.presentation.models.VacanciesAmountUI

class VacanciesAmountMapperImpl : VacanciesAmountMapper {
    override fun map(domainModel: VacanciesAmount): VacanciesAmountUI {
        return VacanciesAmountUI(
            count = domainModel.count
        )
    }
}