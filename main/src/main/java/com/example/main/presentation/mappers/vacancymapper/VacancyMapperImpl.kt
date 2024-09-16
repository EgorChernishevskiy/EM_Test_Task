package com.example.main.presentation.mappers.vacancymapper

import com.example.main.domain.models.Vacancy
import com.example.main.presentation.models.AddressUI
import com.example.main.presentation.models.ExperienceUI
import com.example.main.presentation.models.SalaryUI
import com.example.main.presentation.models.VacancyUI

class VacancyMapperImpl : VacancyMapper {
    override fun map(domainModel: Vacancy): VacancyUI {
        return VacancyUI(
            id = domainModel.id,
            lookingNumber = domainModel.lookingNumber,
            title = domainModel.title,
            company = domainModel.company,
            publishedDate = domainModel.publishedDate,
            schedules = domainModel.schedules,
            description = domainModel.description,
            questions = domainModel.questions,
            responsibilities = domainModel.responsibilities,
            appliedNumber = domainModel.appliedNumber,
            address = AddressUI(
                town = domainModel.address.town,
                street = domainModel.address.street,
                house = domainModel.address.house
            ),
            experience = ExperienceUI(
                previewText = domainModel.experience.previewText,
                text = domainModel.experience.text
            ),
            salary = SalaryUI(
                full = domainModel.salary.full,
                short = domainModel.salary.short
            ),
            isFavorite = domainModel.isFavorite
        )
    }
}