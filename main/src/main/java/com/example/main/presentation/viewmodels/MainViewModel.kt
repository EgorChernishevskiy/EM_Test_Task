package com.example.main.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.Vacancy
import com.example.core.domain.usecases.GetFavoritesFlowUseCase
import com.example.core.domain.usecases.InsertFavoriteUseCase
import com.example.core.presentation.adapters.IAdapterDelegate
import com.example.main.domain.usecases.GetOffersUseCase
import com.example.main.domain.usecases.GetVacanciesCountUseCase
import com.example.core.domain.usecases.GetVacanciesUseCase
import com.example.main.presentation.mappers.offermapper.OfferMapper
import com.example.main.presentation.mappers.vacanciesamountmapper.VacanciesAmountMapper
import com.example.core.presentation.mappres.vacancymapper.VacancyMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getVacanciesCountUseCase: GetVacanciesCountUseCase,
    private val offerMapper: OfferMapper,
    private val vacancyMapper: VacancyMapper,
    private val vacanciesAmountMapper: VacanciesAmountMapper,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase
) : ViewModel() {

    private val _vacancies = MutableLiveData<List<IAdapterDelegate>>()
    val vacancies: LiveData<List<IAdapterDelegate>> = _vacancies

    private val _offers = MutableLiveData<List<IAdapterDelegate>>()
    val offers: LiveData<List<IAdapterDelegate>> = _offers

    private val _vacanciesAmount = MutableLiveData<List<IAdapterDelegate>>()
    val vacanciesAmount: LiveData<List<IAdapterDelegate>> = _vacanciesAmount

    fun getVacanciesShort() {
        viewModelScope.launch {
            val domainVacancies = getVacanciesUseCase.execute().take(3)
            val updatedVacancies = updateVacanciesWithFavorites(domainVacancies)
            val vacancyUIList = updatedVacancies.map { vacancy -> vacancyMapper.map(vacancy) }

            val domainVacanciesAmount = getVacanciesCountUseCase.execute()
            val vacanciesAmountUI = vacanciesAmountMapper.map(domainVacanciesAmount)

            val updatedList = vacancyUIList + listOf(vacanciesAmountUI)
            _vacancies.postValue(updatedList)
        }
    }

    fun getVacanciesFull() {
        viewModelScope.launch {
            val domainVacancies = getVacanciesUseCase.execute()
            val updatedVacancies = updateVacanciesWithFavorites(domainVacancies)
            val vacancyUIList = updatedVacancies.map { vacancy -> vacancyMapper.map(vacancy) }
            _vacancies.postValue(vacancyUIList)
        }
    }

    fun getOffers() {
        viewModelScope.launch {
            val domainOffers = getOffersUseCase.execute()
            val offerUIList = domainOffers.map { offer -> offerMapper.map(offer) }
            _offers.postValue(offerUIList)
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

    fun addFavorite(id: String, isFull: Boolean) {
        viewModelScope.launch {
            insertFavoriteUseCase.execute(id)
            if (isFull) {
                getVacanciesFull()
            } else {
                getVacanciesShort()
            }
        }
    }

}
