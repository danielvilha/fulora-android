package com.fulora.app.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fulora.app.home.use_case.PlantingAreaUseCase

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 *
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the FuloraDatabaseDao and context to the ViewModel.
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val plantingUseCase: PlantingAreaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(plantingUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}