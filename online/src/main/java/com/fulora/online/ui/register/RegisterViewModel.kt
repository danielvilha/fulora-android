package com.fulora.online.ui.register

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.fulora.online.R
import com.fulora.online.hideKeyboard
import com.fulora.online.model.PlantingArea
import com.fulora.online.source.remote.RealtimeDatabaseManager
import com.fulora.preference.getUserUid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
@SuppressLint("StaticFieldLeak")
class RegisterViewModel(private val context: Context): ViewModel() {

    fun savePlant(fragment: RegisterFragment, name: String, picture: String, dateTime: String) {
        fragment.requireView().hideKeyboard()
        val userId = getUserUid(fragment.requireContext())

        val plantingArea = PlantingArea(
            name = name,
            picture = picture,
            userId = userId,
            dateTime = dateTime
        )

        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = RealtimeDatabaseManager().addPlant(uid = userId, planting = plantingArea)
            goToHome(fragment, isSuccess)
        }
    }

    private suspend fun goToHome(fragment: RegisterFragment, isSuccess: Boolean) {
        delay(1000L)

        if (isSuccess) {
            viewModelScope.launch(Dispatchers.Main) {
                fragment.findNavController().popBackStack()
            }
        } else {
            Toast.makeText(context, fragment.getString(R.string.please_try_again), Toast.LENGTH_LONG).show()
        }
    }
}