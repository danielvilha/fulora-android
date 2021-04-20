package com.fulora.app.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.fulora.app.R
import com.fulora.app.databinding.FragmentOnboardingBinding
import com.fulora.app.di.Injector
import com.fulora.app.ui.onboarding.enums.OnboardingSteps

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding, container, false)

        viewModel = Injector.provideOnboardingViewModel(this)

        binding.buttonBack.setOnClickListener {
            if (binding.buttonBack.text == getString(R.string.pular)) {
                viewModel.goToLogin()
            } else {
                binding.viewpager.currentItem = binding.viewpager.currentItem - 1
            }
        }

        binding.buttonNext.setOnClickListener {
            if (binding.buttonNext.text == getString(R.string.comecar)) {
                viewModel.goToLogin()
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
                        binding.buttonBack.text = getString(R.string.pular)
                    }
                    OnboardingSteps.ONBOARDING_STEP_THREE -> {
                        binding.buttonNext.text = getString(R.string.comecar)
                    }
                    else -> {
                        binding.buttonBack.text = getString(R.string.voltar)
                        binding.buttonNext.text = getString(R.string.proximo)
                    }
                }
            }
        })

        return binding.root
    }
}