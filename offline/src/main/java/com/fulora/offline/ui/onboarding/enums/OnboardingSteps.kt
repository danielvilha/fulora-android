package com.fulora.offline.ui.onboarding.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fulora.offline.R

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 */
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