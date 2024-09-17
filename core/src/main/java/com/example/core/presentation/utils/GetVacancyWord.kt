package com.example.core.presentation.utils

fun getVacancyWord(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "вакансия"
        count % 10 in 2..4 && count % 100 !in 12..14 -> "вакансии"
        else -> "вакансий"
    }
}