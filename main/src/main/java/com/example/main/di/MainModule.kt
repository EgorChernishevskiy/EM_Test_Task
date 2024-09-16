package com.example.main.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.main.data.api.ApiService
import com.example.main.data.mappres.offermapper.DataOfferMapperImpl
import com.example.main.data.mappres.offermapper.IOfferMapper
import com.example.main.data.mappres.vacancymapper.DataVacancyMapperImpl
import com.example.main.data.mappres.vacancymapper.IVacancyMapper
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
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/1d9b332c-d248-45f1-95a8-2b66f21498b8/"

val mainModule = module {
    single {

        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
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