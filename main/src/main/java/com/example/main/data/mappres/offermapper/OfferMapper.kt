package com.example.main.data.mappres.offermapper

import com.example.main.data.models.OfferDTO
import com.example.main.domain.models.Offer

interface OfferMapper {
    fun mapOfferDTOToOffer(dto: OfferDTO): Offer
}