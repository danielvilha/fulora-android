package com.fulora.app.onboarding.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fulora.app.databinding.ItemOnboardingBinding
import com.fulora.app.onboarding.OnboardingSteps

class OnboardingStepsViewHolder(val binding: ItemOnboardingBinding) :
    RecyclerView.ViewHolder(binding.root) {

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