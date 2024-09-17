package com.example.main.data.models.responses

import com.example.main.data.models.dto.OfferDTO
import com.example.core.data.models.VacancyDTO

data class SearchResponse(
    val offers: List<OfferDTO>,
    val vacancies: List<VacancyDTO>
)