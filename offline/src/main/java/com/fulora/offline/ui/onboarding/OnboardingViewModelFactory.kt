package com.fulora.offline.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class OnboardingViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return OnboardingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}