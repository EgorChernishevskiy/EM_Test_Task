package com.example.core.data.models.dto

data class OfferDTO(
    val id: String?,
    val title: String,
    val link: String,
    val button: ButtonDTO? = null
)