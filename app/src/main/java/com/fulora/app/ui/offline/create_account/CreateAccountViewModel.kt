package com.fulora.app.ui.offline.create_account

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
class CreateAccountViewModel(private val fragment: CreateAccountFragment): ViewModel() {

    fun createAccount(name: String, email: String, password: String) {
        val sharedPreferences: SharedPreferences =
            fragment.requireContext()
                .getSharedPreferences("FuloraSharedPref", Context.MODE_PRIVATE)

        // Creating an Editor object to edit(write to the file)
        // Creating an Editor object to edit(write to the file)
        val myEdit = sharedPreferences.edit()

        myEdit.putString("USER_NAME", name)
        myEdit.putString("USER_EMAIL", email)
        myEdit.putString("USER_PASSWORD", password)


    }
}