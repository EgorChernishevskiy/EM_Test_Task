package com.example.auth.presentation.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.auth.R
import com.example.auth.databinding.FragmentPinBinding
import com.example.auth.presentation.navigation.AuthNavigation
import com.example.auth.presentation.viewmodels.PinViewModel

const val EMAIL_KEY = "EMAIL_KEY"

class PinFragment : Fragment(R.layout.fragment_pin) {

    private var _binding: FragmentPinBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPinBinding.inflate(inflater, container, false)
        bindUI()
        observeVM()
        return binding.root
    }

    private fun bindUI() {
        binding.pin.openKeyboard()
        binding.pin.doAfterTextChanged {
            viewModel.changePin(it.toString())
        }
        binding.titleRec.text = "${binding.titleRec.text} ${arguments?.getString(EMAIL_KEY)}"
        binding.button.setOnClickListener {
            requireActivity().hideKeyboard()
            (requireActivity() as AuthNavigation).closeAuth()
        }
    }

    private fun observeVM() {
        viewModel.isButtonEnabled.observe(viewLifecycleOwner) {
            binding.button.isEnabled = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun EditText.openKeyboard() {
        if (this.requestFocus()) {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun Activity.hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) view = View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
