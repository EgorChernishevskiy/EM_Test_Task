package com.example.main.domain.models

import androidx.annotation.DrawableRes

data class Offer(
    val id: String,
    val title: String,
    val link: String,
    @DrawableRes val img: Int,
    val button: Button? = null
)