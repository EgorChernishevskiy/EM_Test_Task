package com.example.main.di

import com.example.main.data.api.SearchApiService
import com.example.main.data.mappres.DataOfferMapperImpl
import com.example.main.data.mappres.IOfferMapper
import com.example.core.data.mappres.DataVacancyMapperImpl
import com.example.core.data.mappres.IVacancyMapper
import com.example.main.data.repositories.SearchRepositoryImpl
import com.example.main.domain.repositories.ISearchRepository
import com.example.main.domain.usecases.GetOffersUseCase
import com.example.main.domain.usecases.GetVacanciesCountUseCase
import com.example.main.domain.usecases.GetVacanciesUseCase
import com.example.main.presentation.mappers.offermapper.OfferMapper
import com.example.main.presentation.mappers.offermapper.OfferMapperImpl
import com.example.main.presentation.mappers.vacanciesamountmapper.VacanciesAmountMapper
import com.example.main.presentation.mappers.vacanciesamountmapper.VacanciesAmountMapperImpl
import com.example.main.presentation.mappers.vacancymapper.VacancyMapper
import com.example.main.presentation.mappers.vacancymapper.VacancyMapperImpl
import com.example.main.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {

    single {
        get<Retrofit>().create(SearchApiService::class.java)
    }

    single<IOfferMapper> { DataOfferMapperImpl() }
    single<IVacancyMapper> { DataVacancyMapperImpl() }
    single<OfferMapper> { OfferMapperImpl() }
    single<VacancyMapper> { VacancyMapperImpl() }
    single<VacanciesAmountMapper> { VacanciesAmountMapperImpl() }

    factory<ISearchRepository> {
        SearchRepositoryImpl(
            get(), get(), get()
        )
    }

    factory { GetOffersUseCase(get()) }
    factory { GetVacanciesUseCase(get()) }
    factory { GetVacanciesCountUseCase(get()) }

    viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }

}