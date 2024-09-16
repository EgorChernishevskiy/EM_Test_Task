package com.example.main.presentation.adapters

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.core.presentation.adapters.IAdapterDelegate
import com.example.main.databinding.ItemRecommendationBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.example.main.presentation.models.OfferUI

fun offerAdapterDelegate(
    onClick: (String) -> Unit
) =
    adapterDelegateViewBinding<OfferUI, IAdapterDelegate, ItemRecommendationBinding>({ layoutInflater, root ->
        ItemRecommendationBinding.inflate(layoutInflater, root, false)
    }) {

        binding.root.setOnClickListener {
            onClick(item.link)
        }

        bind {
            if (item.img > 0) {
                binding.imgRec.setImageDrawable(AppCompatResources.getDrawable(context, item.img))
            } else {
                binding.imgRec.isVisible = false
            }

            binding.titleRec.text = item.title

            binding.buttonRec.isVisible = item.buttonText != null
            binding.buttonRec.text = item.buttonText
        }
    }
