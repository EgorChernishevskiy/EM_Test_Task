package com.example.main.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.adapters.IAdapterDelegate
import com.example.main.domain.usecases.GetOffersUseCase
import com.example.main.domain.usecases.GetVacanciesCountUseCase
import com.example.main.domain.usecases.GetVacanciesUseCase
import com.example.main.presentation.mappers.offermapper.OfferMapper
import com.example.main.presentation.mappers.vacanciesamountmapper.VacanciesAmountMapper
import com.example.main.presentation.mappers.vacancymapper.VacancyMapper
import kotlinx.coroutines.launch

class MainViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getVacanciesCountUseCase: GetVacanciesCountUseCase,
    private val offerMapper: OfferMapper,
    private val vacancyMapper: VacancyMapper,
    private val vacanciesAmountMapper: VacanciesAmountMapper
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
            val vacancyUIList = domainVacancies.map { vacancy -> vacancyMapper.map(vacancy) }

            val domainVacanciesAmount = getVacanciesCountUseCase.execute()
            val vacanciesAmountUI = vacanciesAmountMapper.map(domainVacanciesAmount)

            val updatedList = vacancyUIList + listOf(vacanciesAmountUI)
            _vacancies.postValue(updatedList)
        }
    }

    fun getVacanciesFull() {
        viewModelScope.launch {
            val domainVacancies = getVacanciesUseCase.execute()
            val vacancyUIList = domainVacancies.map { vacancy -> vacancyMapper.map(vacancy) }
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

//    fun addFavorite(id: String, isFull: Boolean) {
//        viewModelScope.launch {
//            repository.insertFavorite(id)
//            if (isFull) getVacanciesFull() else getVacanciesShort()
//        }
//    }
}
