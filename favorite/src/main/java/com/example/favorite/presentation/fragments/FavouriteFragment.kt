package com.example.favorite.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.presentation.navigation.Navigation
import com.example.core.presentation.utils.getVacancyWord
import com.example.favorite.R
import com.example.favorite.databinding.FragmentFavouriteBinding
import com.example.favorite.presentation.adapters.vacancyAdapterDelegate
import com.example.favorite.presentation.viewmodels.FavouriteViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val vm: FavouriteViewModel by viewModel()

    private val favorites = ListDelegationAdapter(
        vacancyAdapterDelegate({
            (requireActivity() as Navigation).navigateToVacancy(it)
        }, {
            vm.addFavorite(it)
        })
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        vm.getFavorites()
        bindUI()
        observeVM()
        return binding.root
    }

    private fun bindUI() {
        binding.favorites.adapter = favorites
    }

    private fun observeVM() {
        vm.vacancies.observe(viewLifecycleOwner) {
            favorites.items = it
            favorites.notifyDataSetChanged()
            val vacanciesText = "${it.size} ${getVacancyWord(it.size)}"
            binding.textVacancyCount.text = vacanciesText
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

