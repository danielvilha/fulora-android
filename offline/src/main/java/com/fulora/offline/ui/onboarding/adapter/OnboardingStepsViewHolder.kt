package com.fulora.offline.ui.onboarding.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fulora.offline.databinding.ItemOnboardingBinding
import com.fulora.offline.ui.onboarding.enums.OnboardingSteps

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 */
class OnboardingStepsViewHolder(private val binding: ItemOnboardingBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(step: OnboardingSteps) {
        binding.imageOnboarding.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                step.image
            )
        )
        binding.titleOnboarding.text = binding.root.context.getString(step.title)
        binding.subtitleOnboarding.text = binding.root.context.getString(step.subTitle)
    }
}