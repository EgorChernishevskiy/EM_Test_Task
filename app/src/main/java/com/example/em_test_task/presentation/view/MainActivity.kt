package com.example.em_test_task.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.auth.presentation.fragments.EMAIL_KEY
import com.example.auth.presentation.fragments.LoginFragment
import com.example.auth.presentation.fragments.PinFragment
import com.example.auth.presentation.navigation.AuthNavigation
import com.example.core.presentation.models.VacancyUI
import com.example.core.presentation.navigation.Navigation
import com.example.em_test_task.R
import com.example.em_test_task.databinding.ActivityMainBinding
import com.example.em_test_task.presentation.viewmodel.CommonViewModel
import com.example.vacancydetails.presentation.fragments.VACANCY_KEY
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), AuthNavigation, Navigation {

    private lateinit var binding: ActivityMainBinding
    private val vm: CommonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.handleVacancies()
        vm.loadFavoriteVacancies()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener {
            toggleBottomNavigationVisibility()
        }

        setupBottomNav()
        observeVM()
    }

    override fun navigateToPin(email: String) {
        val bundle = Bundle().apply {
            putString(EMAIL_KEY, email)
        }
        findNavController(R.id.fragmentContainer).navigate(R.id.pin, bundle)
    }

    override fun closeAuth() {
        val navController = findNavController(R.id.fragmentContainer)

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.auth, true)
            .setPopUpTo(R.id.pin, true)
            .build()

        navController.navigate(R.id.navigation_main, null, navOptions)
    }

    private fun toggleBottomNavigationVisibility() {
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (currentFragment is LoginFragment || currentFragment is PinFragment) {
            navView.visibility = View.GONE
        } else {
            navView.visibility = View.VISIBLE
        }
    }

    override fun navigateToVacancy(vacancy: VacancyUI) {
        val navController =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                ?.findNavController()
        navController?.navigate(R.id.to_vacancyFragment, bundleOf(VACANCY_KEY to vacancy))
    }

    override fun navigateBack() {
        findNavController(R.id.fragmentContainer).popBackStack()
    }

    private fun observeVM() {
        vm.favouriteCount.observe(this) { count ->
            binding.navView.getOrCreateBadge(R.id.navigation_favourite).apply {
                backgroundColor =
                    ContextCompat.getColor(this@MainActivity, com.example.core.R.color.red)
                badgeTextColor =
                    ContextCompat.getColor(this@MainActivity, com.example.core.R.color.white)
                isVisible = count > 0
                number = count
            }
        }
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.fragmentContainer)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.auth, R.id.pin -> {
                    binding.navView.visibility = View.GONE
                }

                else -> {
                    binding.navView.visibility = View.VISIBLE
                }
            }
        }
        binding.navView.setupWithNavController(navController)
    }

}