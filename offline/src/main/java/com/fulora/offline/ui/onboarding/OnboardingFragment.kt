package com.fulora.offline.ui.onboarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.fulora.offline.R
import com.fulora.offline.databinding.FragmentOnboardingBinding
import com.fulora.offline.ui.onboarding.enums.OnboardingSteps

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 *
 * This [Fragment] presents the user with the app information.
 */
class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var viewModelFactory: OnboardingViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding, container, false)

        viewModelFactory = OnboardingViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(OnboardingViewModel::class.java)

        binding.buttonBack.setOnClickListener {
            if (binding.buttonBack.text == getString(R.string.jump)) {
                viewModel.goToLogin(this)
            } else {
                binding.viewpager.currentItem = binding.viewpager.currentItem - 1
            }
        }

        binding.buttonNext.setOnClickListener {
            if (binding.buttonNext.text == getString(R.string.start)) {
                viewModel.goToLogin(this)
            } else {
                binding.viewpager.currentItem = binding.viewpager.currentItem + 1
            }
        }

        binding.viewpager.adapter = viewModel.adapter
        binding.indicator.setViewPager(binding.viewpager)
        binding.viewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (OnboardingSteps.values()[position]) {
                    OnboardingSteps.ONBOARDING_STEP_ONE -> {
                        binding.buttonBack.text = getString(R.string.jump)
                    }
                    OnboardingSteps.ONBOARDING_STEP_THREE -> {
                        binding.buttonNext.text = getString(R.string.start)
                    }
                    else -> {
                        binding.buttonBack.text = getString(R.string.back)
                        binding.buttonNext.text = getString(R.string.next)
                    }
                }
            }
        })

        return binding.root
    }
}