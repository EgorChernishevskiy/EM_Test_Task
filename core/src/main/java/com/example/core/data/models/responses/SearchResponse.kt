package com.example.core.data.models.responses

import com.example.core.data.models.dto.OfferDTO
import com.example.core.data.models.dto.VacancyDTO

data class SearchResponse(
    val offers: List<OfferDTO>,
    val vacancies: List<VacancyDTO>
)