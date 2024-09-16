package com.example.main.presentation.mappers.offermapper

import com.example.main.domain.models.Offer
import com.example.main.presentation.models.OfferUI

class OfferMapperImpl : OfferMapper {
    override fun map(domainModel: Offer): OfferUI {
        return OfferUI(
            id = domainModel.id,
            title = domainModel.title,
            link = domainModel.link,
            img = domainModel.img,
            buttonText = domainModel.button?.text
        )
    }
}