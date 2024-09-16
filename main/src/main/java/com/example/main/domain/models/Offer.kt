package com.example.main.domain.models

data class Offer(
    val id: String?,
    val title: String,
    val link: String,
    val img: Int,
    val button: Button? = null
)