package com.example.main.data.mappres

import com.example.core.data.models.dto.OfferDTO
import com.example.main.domain.models.Offer

interface IOfferMapper {
    fun mapOfferDTOToOffer(dto: OfferDTO): Offer
}