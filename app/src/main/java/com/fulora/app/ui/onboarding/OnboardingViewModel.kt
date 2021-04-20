package com.fulora.app.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.fulora.app.ui.onboarding.adapter.OnboardingViewPagerAdapter
import com.fulora.app.ui.onboarding.enums.OnboardingSteps

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
class OnboardingViewModel(private val fragment: OnboardingFragment): ViewModel() {

    val adapter: OnboardingViewPagerAdapter by lazy { OnboardingViewPagerAdapter(OnboardingSteps.values()) }

    fun goToLogin() {
        findNavController(fragment).navigate(OnboardingFragmentDirections.actionOnboardingFragmentToSignInFragment())
    }
}