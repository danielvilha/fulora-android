package com.fulora.app.ui.offline.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class SignInViewModelFactory(private val fragment: SignInFragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}