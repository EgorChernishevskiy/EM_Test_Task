package com.example.main.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.core.presentation.navigation.Navigation
import com.example.main.databinding.FragmentMainBinding
import com.example.main.presentation.adapters.offerAdapterDelegate
import com.example.main.presentation.viewmodels.MainViewModel
import com.example.search.search.adapter.buttonAdapterDelegate
import com.example.search.search.adapter.vacancyAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(com.example.main.R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()
    private var isFullOpen = false

    private val adapterVacancies = ListDelegationAdapter(
        vacancyAdapterDelegate({
            (requireActivity() as Navigation).navigateToVacancy(it)
        }, {
            //vm.addFavorite(it, isFullOpen)
        }),
        buttonAdapterDelegate {
            showFullVacancies()
            viewModel.getVacanciesFull()
            isFullOpen = true
        }
    )

    private val adapterRecommendations = ListDelegationAdapter(
        offerAdapterDelegate {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel.getVacanciesShort()
        viewModel.getOffers()
        observeVM()
        bindUI()
        return binding.root
    }

    private fun bindUI() {
        binding.rvVacancies.adapter = adapterVacancies
        binding.rvRecommendations.adapter = adapterRecommendations
    }

    private fun observeVM() {
        viewModel.vacancies.observe(viewLifecycleOwner) {
            adapterVacancies.items = it
            adapterVacancies.notifyDataSetChanged()
            val vacanciesText = requireContext().resources.getQuantityString(
                com.example.core.R.plurals.plurals_vacancies_full, it.size, it.size
            )
            binding.textVacancies.text = vacanciesText
        }

        viewModel.offers.observe(viewLifecycleOwner) {
            adapterRecommendations.items = it
            adapterRecommendations.notifyDataSetChanged()
        }

        viewModel.vacanciesAmount.observe(viewLifecycleOwner) {
            adapterVacancies.notifyDataSetChanged()
            adapterVacancies.items = adapterVacancies.items?.plus(it)
        }
    }

    private fun showFullVacancies() {
        binding.textVacancies.isVisible = true
        binding.textVacanciesFilter.isVisible = true
        binding.imgVacanciesFilter.isVisible = true
        binding.textForYou.isVisible = false
        binding.rvRecommendations.isVisible = false
        binding.inputSearch.startIconDrawable =
            AppCompatResources.getDrawable(requireContext(), com.example.core.R.drawable.arrow)
        binding.inputSearch.setStartIconOnClickListener {
            showShortVacancies()
        }
    }

    private fun showShortVacancies() {
        isFullOpen = false
        viewModel.getVacanciesShort()
        binding.textVacancies.isVisible = false
        binding.textVacanciesFilter.isVisible = false
        binding.imgVacanciesFilter.isVisible = false
        binding.textForYou.isVisible = true
        binding.rvRecommendations.isVisible = true
        binding.inputSearch.startIconDrawable =
            AppCompatResources.getDrawable(requireContext(), com.example.core.R.drawable.ic_search)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
