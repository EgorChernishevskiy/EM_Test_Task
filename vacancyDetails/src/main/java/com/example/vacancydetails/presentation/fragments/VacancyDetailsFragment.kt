package com.example.vacancydetails.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.core.presentation.models.VacancyUI
import com.example.core.presentation.navigation.Navigation
import com.example.core.presentation.utils.getCorrectPeopleForm
import com.example.vacancydetails.R
import com.example.vacancydetails.databinding.FragmentVacancyDetailsBinding
import com.example.vacancydetails.presentation.adapters.questionAdapterDelegate
import com.example.vacancydetails.presentation.fragments.ResponseFragment.Companion.ARGS
import com.example.vacancydetails.presentation.fragments.ResponseFragment.Companion.TAG
import com.example.vacancydetails.presentation.viewmodels.VacancyDetailsViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

const val VACANCY_KEY = "VACANCY_KEY"

class VacancyDetailsFragment : Fragment(R.layout.fragment_vacancy_details) {

    private var _binding: FragmentVacancyDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyDetailsViewModel by viewModel()
    private var fav: Boolean = false

    private val adapter = ListDelegationAdapter(
        questionAdapterDelegate {
            showBottomSheet(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyDetailsBinding.inflate(inflater, container, false)
        bindUI()
        return binding.root
    }

    private fun bindUI() {
        val args = arguments?.getParcelable<VacancyUI>(VACANCY_KEY) as VacancyUI
        binding.textVacancyName.text = args.title
        binding.textSalary.text =
            getString(R.string.binding_salary, args.salary.full, args.salary.short)
        binding.textExp.text = getString(R.string.binding_exp, args.experience.text)
        binding.textEmployment.text = args.schedules.joinToString(", ")
        val peopleResponseText = getCorrectPeopleForm(args.appliedNumber)
        binding.textPeopleResponse.text =
            getString(R.string.binding_people_responded, peopleResponseText)
        binding.linearLayout.isVisible = args.appliedNumber > 0
        val peopleLook = getCorrectPeopleForm(args.lookingNumber)
        binding.textPeopleLook.text = getString(R.string.binding_people_looking, peopleLook)
        binding.linearLayout2.isVisible = args.lookingNumber > 0
        binding.textCompanyName.text = args.company
        binding.address.text = getString(
            R.string.binding_address,
            args.address.town,
            args.address.street,
            args.address.house
        )
        binding.textCompanyDescription.isVisible = args.description?.isNotEmpty() ?: false
        binding.textCompanyDescription.text = args.description
        binding.textYourTask.text = args.responsibilities
        binding.questions.adapter = adapter
        adapter.items = args.questions

        binding.imgBack.setOnClickListener {
            (requireActivity() as Navigation).navigateBack()
        }

        binding.buttonResponse.setOnClickListener {
            showBottomSheet("")
        }

        binding.imgFavourite.setImageDrawable(
            if (args.isFavorite) AppCompatResources.getDrawable(
                requireContext(),
                com.example.core.R.drawable.ic_favourite_fill
            )
            else AppCompatResources.getDrawable(
                requireContext(),
                com.example.core.R.drawable.ic_favourite
            )
        )

        fav = args.isFavorite

        binding.imgFavourite.setOnClickListener {
            fav = !fav
            binding.imgFavourite.setImageDrawable(
                if (fav) AppCompatResources.getDrawable(
                    requireContext(),
                    com.example.core.R.drawable.ic_favourite_fill
                )
                else AppCompatResources.getDrawable(
                    requireContext(),
                    com.example.core.R.drawable.ic_favourite
                )
            )
            viewModel.addFavorite(args.id)
        }
    }

    private fun showBottomSheet(question: String = "") {
        ResponseFragment().apply {
            arguments = bundleOf(ARGS to question)
        }.show(childFragmentManager, TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}