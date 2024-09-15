package com.example.auth.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.auth.R
import com.example.auth.databinding.FragmentLoginBinding
import com.example.auth.presentation.navigation.AuthNavigation
import com.example.auth.presentation.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        bindUI()
        observeVM()
        return binding.root
    }

    private fun bindUI() {
        binding.buttonContinue.setOnClickListener {
            if (viewModel.canNavigate()) {
                (requireActivity() as AuthNavigation).navigateToPin(binding.editTextEmail.text.toString())
            }
        }

        binding.editTextEmail.doAfterTextChanged {
            viewModel.checkEmail(it.toString())
        }

        binding.inputEmail.setEndIconOnClickListener {
            binding.editTextEmail.background = AppCompatResources.getDrawable(
                requireContext(),
                com.example.core.R.drawable.background_edit
            )
            binding.editTextEmail.setText("")
            viewModel.clearError()
        }
    }

    private fun observeVM() {
        viewModel.isButtonEnabled.observe(viewLifecycleOwner) {
            binding.buttonContinue.isEnabled = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            binding.textError.text = it
            binding.editTextEmail.background =
                if (it.isEmpty()) AppCompatResources.getDrawable(
                    requireContext(),
                    com.example.core.R.drawable.background_edit
                )
                else AppCompatResources.getDrawable(
                    requireContext(),
                    com.example.core.R.drawable.background_edit_error
                )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
