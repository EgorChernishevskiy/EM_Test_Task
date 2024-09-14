package com.example.auth.presentation.navigation

interface AuthNavigation {
    fun navigateToPin(email: String)
    fun closeAuth()
}