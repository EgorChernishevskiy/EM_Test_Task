package com.example.core.di

import com.example.core.utils.ResourceProvider
import org.koin.dsl.module

val coreModule = module {
    single { ResourceProvider(get()) }
}