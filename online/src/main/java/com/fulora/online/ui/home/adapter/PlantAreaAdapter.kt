package com.fulora.online.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fulora.online.R
import com.fulora.online.databinding.ItemPlantingAreaBinding
import com.fulora.online.model.PlantingArea

/**
 * Created by danielvilha on 04/05/21
 * https://github.com/danielvilha
 */
class PlantAreaAdapter(val context: Context, val platings: List<PlantingArea>): RecyclerView.Adapter<PlantAreaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantAreaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ItemPlantingAreaBinding>(
            layoutInflater,
            R.layout.item_planting_area,
            parent,
            false
        )

        return PlantAreaViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: PlantAreaViewHolder, position: Int) {
        holder.bind(platings[position])
    }

    override fun getItemCount() = platings.size
}