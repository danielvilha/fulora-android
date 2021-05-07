package com.fulora.offline.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.fulora.offline.ui.onboarding.adapter.OnboardingViewPagerAdapter
import com.fulora.offline.ui.onboarding.enums.OnboardingSteps

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 */
class OnboardingViewModel : ViewModel() {

    val adapter: OnboardingViewPagerAdapter by lazy { OnboardingViewPagerAdapter(OnboardingSteps.values()) }

    fun goToLogin(fragment: OnboardingFragment) {
        fragment.findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
    }
}