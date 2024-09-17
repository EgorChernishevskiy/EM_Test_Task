package com.example.core.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteVacancyUI(
    val id: String,
    val isFavorite: Boolean
) : Parcelable
