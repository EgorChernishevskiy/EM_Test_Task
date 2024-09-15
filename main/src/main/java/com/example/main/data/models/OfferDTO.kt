package com.example.main.data.models

data class OfferDTO(
    val id: String,
    val title: String,
    val link: String,
    val button: ButtonDTO? = null
)