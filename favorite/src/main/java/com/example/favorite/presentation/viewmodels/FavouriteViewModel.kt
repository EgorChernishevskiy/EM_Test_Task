package com.example.favorite.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.Vacancy
import com.example.core.domain.usecases.GetFavoritesFlowUseCase
import com.example.core.presentation.adapters.IAdapterDelegate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase

) : ViewModel() {

    private val _vacancies = MutableLiveData<List<IAdapterDelegate>>()
    val vacancies: LiveData<List<IAdapterDelegate>> = _vacancies

    fun getFavorites() {
        viewModelScope.launch {
            val domainVacancies = getVacanciesUseCase.execute()

            val updatedVacancies = updateVacanciesWithFavorites(domainVacancies)

            val favoriteVacancies = updatedVacancies.filter { it.isFavorite }

            val vacancyUIList = favoriteVacancies.map { vacancy -> vacancyMapper.map(vacancy) }
            _vacancies.postValue(vacancyUIList)
        }
    }

    private suspend fun updateVacanciesWithFavorites(vacancies: List<Vacancy>): List<Vacancy> {
        val favoritesFlow = getFavoritesFlowUseCase.execute()
        val favoriteIds = favoritesFlow.first().map { it.id }.toSet()

        return vacancies.map { vacancy ->
            if (vacancy.id in favoriteIds) {
                vacancy.copy(isFavorite = true)
            } else {
                vacancy.copy(isFavorite = false)
            }
        }
    }

    fun addFavorite(id: String) {
        viewModelScope.launch {
            repository.insertFavorite(id)
            getFavorites()
        }
    }
}
