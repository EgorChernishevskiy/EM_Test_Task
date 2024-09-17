package com.example.em_test_task.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetFavoritesFlowUseCase
import com.example.core.domain.usecases.InsertFavoriteUseCase
import com.example.core.presentation.mappres.IFavoriteVacancyMapperUI
import com.example.core.presentation.models.FavoriteVacancyUI
import com.example.main.domain.usecases.GetVacanciesUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CommonViewModel(
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase,
    private val favoriteVacancyMapperUI: IFavoriteVacancyMapperUI,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    private val _favouriteCount = MutableLiveData<Int>()
    val favouriteCount: LiveData<Int> = _favouriteCount

    private val _favoriteVacancies = MutableLiveData<List<FavoriteVacancyUI>>()
    val favoriteVacancies: LiveData<List<FavoriteVacancyUI>> = _favoriteVacancies

    fun loadFavoriteVacancies() {
        viewModelScope.launch {
            getFavoritesFlowUseCase.execute()
                .onEach { favorites ->
                    val favoriteVacancyUIList = favorites.map { favorite ->
                        favoriteVacancyMapperUI.favoriteVacancyToUI(favorite)
                    }
                    _favoriteVacancies.value = favoriteVacancyUIList
                    _favouriteCount.value = favoriteVacancyUIList.size
                }
                .launchIn(viewModelScope)
        }
    }
    fun handleVacancies() {
        viewModelScope.launch {
            val domainVacancies = getVacanciesUseCase.execute()
            domainVacancies.filter { it.isFavorite }.forEach { vacancy ->
                insertFavoriteUseCase.execute(vacancy.id)
            }
        }
    }
}

