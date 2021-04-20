package com.fulora.app.ui.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fulora.app.ui.home.HomeViewModel

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class SplashScreenViewModelFactory(private var fragment: SplashScreenFragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}