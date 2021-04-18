package com.fulora.app.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fulora.app.R
import com.fulora.app.databinding.ItemOnboardingBinding
import com.fulora.app.onboarding.OnboardingSteps

class OnboardingViewPagerAdapter(val steps: Array<OnboardingSteps>) :
    RecyclerView.Adapter<OnboardingStepsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingStepsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ItemOnboardingBinding>(
            layoutInflater,
            R.layout.item_onboarding,
            parent,
            false
        )
        return OnboardingStepsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingStepsViewHolder, position: Int) {
        holder.bind(steps[position])
    }

    override fun getItemCount(): Int {
        return steps.size
    }
}
