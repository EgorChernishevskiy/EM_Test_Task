package com.example.vacancydetails.di

import com.example.vacancydetails.presentation.viewmodels.VacancyDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vacancyDetailsModule = module {

    viewModel { VacancyDetailsViewModel(get()) }

}