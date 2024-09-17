package com.example.main.data.mappres

import com.example.main.R
import com.example.core.data.models.dto.OfferDTO
import com.example.main.domain.models.Button
import com.example.main.domain.models.Offer

class DataOfferMapperImpl : IOfferMapper {
    override fun mapOfferDTOToOffer(dto: OfferDTO): Offer {
        return Offer(
            id = dto.id,
            title = dto.title,
            link = dto.link,
            img = iconSelection(dto?.id ?: ""),
            button = dto.button?.let { Button(text = it.text) }
        )
    }

    private fun iconSelection(id: String): Int {
        return when (id) {
            "near_vacancies" -> R.drawable.ic_blue
            "level_up_resume" -> R.drawable.ic_star
            "temporary_job" -> R.drawable.ic_list
            else -> 0
        }
    }
}