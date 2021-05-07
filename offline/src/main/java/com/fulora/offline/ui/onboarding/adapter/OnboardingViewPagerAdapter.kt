package com.fulora.offline.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fulora.offline.R
import com.fulora.offline.databinding.ItemOnboardingBinding
import com.fulora.offline.ui.onboarding.enums.OnboardingSteps

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 */
class OnboardingViewPagerAdapter(private val steps: Array<OnboardingSteps>): RecyclerView.Adapter<OnboardingStepsViewHolder>() {

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

    override fun getItemCount() = steps.size
}