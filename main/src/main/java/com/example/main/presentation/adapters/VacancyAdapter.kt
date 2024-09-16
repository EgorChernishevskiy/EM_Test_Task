package com.example.search.search.adapter

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.core.databinding.ItemVacancyBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.example.core.presentation.adapters.IAdapterDelegate
import com.example.main.databinding.ItemButtonBigBinding
import com.example.main.presentation.models.VacanciesAmountUI
import com.example.main.presentation.models.VacancyUI

private const val LOOKING_NOW = "Сейчас просматривает"

fun vacancyAdapterDelegate(
    onClick: (VacancyUI) -> Unit,
    onFavoriteClick: (String) -> Unit
) =
    adapterDelegateViewBinding<VacancyUI, IAdapterDelegate, ItemVacancyBinding>({ layoutInflater, root ->
        ItemVacancyBinding.inflate(layoutInflater, root, false)
    }) {
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }

        bind {
            binding.textLookingNow.isVisible = item.lookingNumber > 0
            val people: String =
                context.resources.getQuantityString(
                    com.example.core.R.plurals.plurals_vacancies,
                    item.lookingNumber,
                    item.lookingNumber
                )
            binding.textLookingNow.text = "$LOOKING_NOW $people"

            binding.imgFavourite.setImageDrawable(
                if (item.isFavorite) AppCompatResources.getDrawable(
                    context,
                    com.example.core.R.drawable.ic_favourite_fill
                )
                else AppCompatResources.getDrawable(
                    context,
                    com.example.core.R.drawable.ic_favourite
                )
            )
            binding.imgFavourite.setOnClickListener {
                onFavoriteClick.invoke(item.id)
            }
            binding.textVacancy.text = item.title
            binding.textCity.text = item.address.town
            binding.textExperience.text = item.experience.previewText
            binding.textDate.text = item.publishedDate
        }
    }

private const val MORE = "Еще"

fun buttonAdapterDelegate(
    onClick: () -> Unit
) =
    adapterDelegateViewBinding<VacanciesAmountUI, IAdapterDelegate, ItemButtonBigBinding>({ layoutInflater, root ->
        ItemButtonBigBinding.inflate(layoutInflater, root, false)
    }) {
        binding.buttonMore.setOnClickListener {
            onClick.invoke()
        }

        bind {
            val text =
                context.resources.getQuantityString(
                    com.example.core.R.plurals.plurals_button,
                    item.count,
                    item.count
                )
            binding.buttonMore.text = "$MORE $text"
        }
    }
