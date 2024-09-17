package com.example.favorite.presentation.adapters

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.core.databinding.ItemVacancyBinding
import com.example.core.presentation.adapters.IAdapterDelegate
import com.example.core.presentation.models.VacancyUI
import com.example.core.presentation.utils.getCorrectPeopleForm
import com.example.favorite.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

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
                getCorrectPeopleForm(item.lookingNumber)
            binding.textLookingNow.text = context.getString(R.string.looking_now, people)

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
