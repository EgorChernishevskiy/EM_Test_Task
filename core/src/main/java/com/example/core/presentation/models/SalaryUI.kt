package com.example.core.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalaryUI(
    val full: String,
    val short: String? = null
) : Parcelable