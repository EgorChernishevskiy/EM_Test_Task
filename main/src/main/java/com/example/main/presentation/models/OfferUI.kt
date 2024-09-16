package com.example.main.presentation.models

import androidx.annotation.DrawableRes
import com.example.core.presentation.adapters.IAdapterDelegate

data class OfferUI(
    val id: String?,
    val title: String,
    val link: String,
    @DrawableRes val img: Int,
    val buttonText: String?
) : IAdapterDelegate