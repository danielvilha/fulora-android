package com.fulora.online.ui.home.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.fulora.online.R
import com.fulora.online.databinding.ItemPlantingAreaBinding
import com.fulora.online.model.PlantingArea

/**
 * Created by danielvilha on 04/05/21
 * https://github.com/danielvilha
 */
class PlantAreaViewHolder(val context: Context, val binding: ItemPlantingAreaBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(plant: PlantingArea) {
        binding.imagePlantation.setImageDrawable(context.getDrawable(R.drawable.ic_plant_5))
        binding.textPlantName.text = plant.name
        binding.textWatered.text = plant.dateTime
        binding.imageIcon.setImageDrawable(context.getDrawable(R.drawable.ic_user))
        binding.cardView.background = context.getDrawable(R.color.panache)
        binding.floating.background = context.getDrawable(R.color.malibu)
        binding.floating.setImageDrawable(context.getDrawable(R.drawable.ic_correct))
    }
}