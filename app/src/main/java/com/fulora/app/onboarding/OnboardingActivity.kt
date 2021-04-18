package com.fulora.app.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.fulora.app.R
import com.fulora.app.databinding.ActivityOnboardingBinding
import com.fulora.app.onboarding.adapter.OnboardingViewPagerAdapter
import com.fulora.app.sign_in.SignInActivity

class OnboardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnboardingBinding

    val adapter: OnboardingViewPagerAdapter by lazy { OnboardingViewPagerAdapter(OnboardingSteps.values()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        initView()
    }

    private fun initView() {
        initViewPager()
        binding.buttonBack.setOnClickListener {
            if (binding.buttonBack.text == getString(R.string.pular)) {
                goToLogin()
            } else {
                binding.viewpager.currentItem = binding.viewpager.currentItem - 1
            }
        }

        binding.buttonNext.setOnClickListener {
            if (binding.buttonNext.text == getString(R.string.comecar)) {
                goToLogin()
            } else {
                binding.viewpager.currentItem = binding.viewpager.currentItem + 1
            }
        }
    }

    private fun goToLogin() {
        startActivity(SignInActivity.createIntent(this))
        finish()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, OnboardingActivity::class.java)
        }
    }

    private fun initViewPager() {
        binding.viewpager.adapter = adapter
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
    }
}


enum class OnboardingSteps(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val subTitle: Int
) {
    ONBOARDING_STEP_ONE(
        R.drawable.onboarding_step_one,
        R.string.onboarding_step_one_title,
        R.string.onboarding_step_one_subtitle
    ),
    ONBOARDING_STEP_TWO(
        R.drawable.onboarding_step_two,
        R.string.onboarding_step_two_title,
        R.string.onboarding_step_two_subtitle
    ),
    ONBOARDING_STEP_THREE(
        R.drawable.onboarding_step_three,
        R.string.onboarding_step_three_title,
        R.string.onboarding_step_three_subtitle
    )

}