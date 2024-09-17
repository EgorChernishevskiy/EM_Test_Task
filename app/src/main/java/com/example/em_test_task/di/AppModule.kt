package com.example.em_test_task.di

import com.example.auth.di.authModule
import com.example.core.di.coreModule
import com.example.main.di.mainModule
import com.example.vacancydetails.di.vacancyDetailsModule

val appModules = listOf(
    authModule,
    coreModule,
    mainModule,
    vacancyDetailsModule
)