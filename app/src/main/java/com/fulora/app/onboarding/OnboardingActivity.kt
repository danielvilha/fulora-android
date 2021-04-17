package com.fulora.app.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.fulora.app.R
import com.fulora.app.databinding.ActivityOnboardingBinding
import com.fulora.app.onboarding.adapter.OnboardingViewPagerAdapter

class OnboardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnboardingBinding

    val adapter: OnboardingViewPagerAdapter by lazy { OnboardingViewPagerAdapter(OnboardingSteps.values()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        initView()
    }

    fun initView() {
        binding.viewpager.adapter = adapter
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, OnboardingActivity::class.java)
        }
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