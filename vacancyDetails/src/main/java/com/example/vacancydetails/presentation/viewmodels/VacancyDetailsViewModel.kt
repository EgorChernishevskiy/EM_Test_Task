package com.example.vacancydetails.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.InsertFavoriteUseCase
import kotlinx.coroutines.launch

class VacancyDetailsViewModel(
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    fun addFavorite(id: String) {
        viewModelScope.launch {
            insertFavoriteUseCase.execute(id)
        }
    }
}
