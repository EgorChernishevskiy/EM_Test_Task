package com.example.core.presentation.models

import android.os.Parcelable
import com.example.core.presentation.adapters.IAdapterDelegate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VacancyUI(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: AddressUI,
    val company: String,
    val experience: ExperienceUI,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryUI,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
) : IAdapterDelegate, Parcelable