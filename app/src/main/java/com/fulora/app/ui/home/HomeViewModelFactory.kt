package com.fulora.app.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fulora.app.source.local.FuloraDatabaseDao

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
    private val dataSource: FuloraDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}