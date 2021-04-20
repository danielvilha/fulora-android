package com.fulora.app.ui.offline.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class CreateAccountViewModelFactory(private val fragment: CreateAccountFragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)) {
            return CreateAccountViewModel(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}