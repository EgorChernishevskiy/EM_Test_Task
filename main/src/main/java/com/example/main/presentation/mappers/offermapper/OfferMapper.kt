package com.example.main.presentation.mappers.offermapper

import com.example.main.domain.models.Offer
import com.example.main.presentation.models.OfferUI

interface OfferMapper {
    fun map(domainModel: Offer): OfferUI
}