package com.example.core.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.data.api.VacancyApiService
import com.example.core.data.mappres.favoritevacancymapper.FavoriteVacancyMapperImpl
import com.example.core.data.mappres.favoritevacancymapper.IFavoriteVacancyMapper
import com.example.core.data.repositories.CommonFavoriteRepositoryImpl
import com.example.core.data.repositories.CommonVacanciesRepositoryImpl
import com.example.core.domain.repositories.ICommonFavoriteRepository
import com.example.core.domain.repositories.ICommonVacanciesRepository
import com.example.core.domain.usecases.GetFavoritesFlowUseCase
import com.example.core.domain.usecases.InsertFavoriteUseCase
import com.example.core.presentation.mappres.favoritevacancymapper.FavoriteVacancyMapperUIImpl
import com.example.core.presentation.mappres.favoritevacancymapper.IFavoriteVacancyMapperUI
import com.example.core.utils.ResourceProvider
import data.local.VacancyDataBase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/1d9b332c-d248-45f1-95a8-2b66f21498b8/"

val coreModule = module {
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
        get<Retrofit>().create(VacancyApiService::class.java)
    }

    single { ResourceProvider(get()) }

    single {
        val db = Room.databaseBuilder(
            get(),
            VacancyDataBase::class.java, "database"
        ).build()
        db.getDao()
    }

    single<IFavoriteVacancyMapper> { FavoriteVacancyMapperImpl() }
    single<IFavoriteVacancyMapperUI> { FavoriteVacancyMapperUIImpl() }

    factory<ICommonFavoriteRepository> {
        CommonFavoriteRepositoryImpl(
            get(), get()
        )
    }
    factory<ICommonVacanciesRepository> {
        CommonVacanciesRepositoryImpl(
            get(), get()
        )
    }

    factory { GetFavoritesFlowUseCase(get()) }
    factory { InsertFavoriteUseCase(get()) }

}