package com.example.favorite.di

import com.example.favorite.presentation.viewmodels.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    viewModel { FavouriteViewModel(get(), get(), get(), get()) }

}