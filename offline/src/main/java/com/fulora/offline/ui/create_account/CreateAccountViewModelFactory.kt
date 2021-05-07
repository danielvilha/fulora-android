package com.fulora.offline.ui.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class CreateAccountViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)) {
            return CreateAccountViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}