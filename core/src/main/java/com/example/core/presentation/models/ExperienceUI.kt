package com.example.core.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExperienceUI(
    val previewText: String,
    val text: String
) : Parcelable