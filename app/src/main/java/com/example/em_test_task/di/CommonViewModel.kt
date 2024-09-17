package com.example.em_test_task.di

import com.example.em_test_task.presentation.viewmodel.CommonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonViewModel = module {
    viewModel { CommonViewModel(get(), get(), get(), get()) }
}
