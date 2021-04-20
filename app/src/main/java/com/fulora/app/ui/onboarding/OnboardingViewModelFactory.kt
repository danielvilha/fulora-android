package com.fulora.app.ui.onboarding

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class OnboardingViewModelFactory(private val fragment: OnboardingFragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return OnboardingViewModel(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}