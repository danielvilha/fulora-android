package com.fulora.online.ui.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.fulora.online.model.PlantingArea
import com.fulora.online.source.remote.RealtimeDatabaseManager
import com.fulora.online.ui.home.adapter.PlantAreaAdapter
import com.fulora.preference.getUserUid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
@SuppressLint("StaticFieldLeak")
class HomeViewModel(private val context: Context) : ViewModel() {

    /**
     * Variable to work with my PlantingArea's
     */
    private var _plantAreas = MutableLiveData<List<PlantingArea>>()
//    val plantAreas: LiveData<List<PlantingArea>>
//        get() = _plantAreas

    /**
     * Show my empty plants view if this is true
     */
    private val _emptyPlants = MutableLiveData<Boolean>()
    val emptyPlants: LiveData<Boolean>
        get() = _emptyPlants

    /**
     * Variable for my PlantArea adapter
     */
    private val _adapter: PlantAreaAdapter by lazy { PlantAreaAdapter(context, _plantAreas.value!!)}

    init {
        _plantAreas.value = mutableListOf()
        getPlantings()
        _emptyPlants.value = _plantAreas.value?.isNullOrEmpty()
    }

    fun toRegister(fragment: HomeFragment) {
        fragment.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterFragment())
    }

    private fun getPlantings() {
        val userId = getUserUid(context)
        var plantAreas = mutableListOf<PlantingArea>()

        viewModelScope.launch(Dispatchers.IO) {
            plantAreas = RealtimeDatabaseManager().getPlantings(userId)
        }

        viewModelScope.launch {
            delay(4000L)
            _plantAreas.value = plantAreas
            _adapter.notifyDataSetChanged()
        }
    }
}