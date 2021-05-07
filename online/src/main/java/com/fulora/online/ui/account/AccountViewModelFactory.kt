package com.fulora.online.ui.account

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 29/04/21
 * https://github.com/danielvilha
 */
@Suppress("UNCHECKED_CAST")
class AccountViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}