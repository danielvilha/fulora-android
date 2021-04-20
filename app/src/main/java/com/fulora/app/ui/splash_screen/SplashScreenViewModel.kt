package com.fulora.app.ui.splash_screen

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
class SplashScreenViewModel(var fragment: SplashScreenFragment): ViewModel()  {

    private var _showScreen = MutableLiveData<Boolean>()
    val showScreen: LiveData<Boolean>
        get() = _showScreen

    init {
        val sharedPreferences: SharedPreferences = fragment.requireContext().getSharedPreferences("USER",
            Context.MODE_PRIVATE
        )

        _showScreen.value = !sharedPreferences.getString("USER_UID", "").isNullOrBlank()
    }
}