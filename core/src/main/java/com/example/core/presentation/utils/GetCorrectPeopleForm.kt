package com.example.core.presentation.utils

fun getCorrectPeopleForm(number: Int): String {
    return if (number % 100 in 11..19) {
        "$number человек"
    } else {
        when (number % 10) {
            1 -> "$number человек"
            2, 3, 4 -> "$number человека"
            else -> "$number человек"
        }
    }
}