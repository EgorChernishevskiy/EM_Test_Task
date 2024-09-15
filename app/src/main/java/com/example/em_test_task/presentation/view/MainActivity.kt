package com.example.em_test_task.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.auth.presentation.fragments.EMAIL_KEY
import com.example.auth.presentation.fragments.LoginFragment
import com.example.auth.presentation.fragments.PinFragment
import com.example.auth.presentation.navigation.AuthNavigation
import com.example.em_test_task.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), AuthNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, LoginFragment())
            }
            findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
        }


        supportFragmentManager.addOnBackStackChangedListener {
            toggleBottomNavigationVisibility()
        }
    }

    override fun navigateToPin(email: String) {
        val pinFragment = PinFragment().apply {
            arguments = Bundle().apply {
                putString(EMAIL_KEY, email)
            }
        }

        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, pinFragment)
            addToBackStack(null)
        }
    }

    override fun closeAuth() {
        finish()
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
}