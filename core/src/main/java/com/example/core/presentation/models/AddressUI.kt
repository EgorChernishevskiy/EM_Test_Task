package com.example.core.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressUI(
    val town: String,
    val street: String,
    val house: String
) : Parcelable