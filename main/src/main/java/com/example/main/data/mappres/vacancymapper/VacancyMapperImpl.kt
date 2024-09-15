package com.example.main.data.mappres.vacancymapper

import com.example.main.data.models.VacancyDTO
import com.example.main.domain.models.Address
import com.example.main.domain.models.Experience
import com.example.main.domain.models.Salary
import com.example.main.domain.models.Vacancy

class VacancyMapperImpl : VacancyMapper {
    override fun mapVacancyDTOToVacancy(dto: VacancyDTO): Vacancy {
        return Vacancy(
            id = dto.id,
            lookingNumber = dto.lookingNumber,
            title = dto.title,
            address = Address(
                town = dto.address.town,
                street = dto.address.street,
                house = dto.address.house
            ),
            company = dto.company,
            experience = Experience(
                previewText = dto.experience.previewText,
                text = dto.experience.text
            ),
            publishedDate = dto.publishedDate,
            isFavorite = dto.isFavorite,
            salary = Salary(
                full = dto.salary.full,
                short = dto.salary.short
            ),
            schedules = dto.schedules,
            appliedNumber = dto.appliedNumber,
            description = dto.description,
            responsibilities = dto.responsibilities,
            questions = dto.questions
        )
    }
}