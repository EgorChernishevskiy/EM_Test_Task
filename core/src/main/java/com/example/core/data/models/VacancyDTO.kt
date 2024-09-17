package com.example.core.data.models

data class VacancyDTO(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: AddressDTO,
    val company: String,
    val experience: ExperienceDTO,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDTO,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)