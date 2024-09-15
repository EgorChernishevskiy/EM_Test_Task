package com.example.auth.di

import com.example.auth.presentation.viewmodels.LoginViewModel
import com.example.auth.presentation.viewmodels.PinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { PinViewModel() }
}
