package com.example.core.utils

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }
}
