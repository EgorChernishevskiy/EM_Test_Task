package com.example.vacancydetails.presentation.adapters

import com.example.vacancydetails.databinding.ItemQuestionBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun questionAdapterDelegate(
    onClick: (String) -> Unit
) = adapterDelegateViewBinding<String, String, ItemQuestionBinding>({ layoutInflater, root ->
    ItemQuestionBinding.inflate(layoutInflater, root, false)
}) {

    binding.root.setOnClickListener {
        onClick(item)
    }

    bind {
        binding.question.text = item
    }
}
