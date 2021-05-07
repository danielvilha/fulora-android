package com.fulora.online.ui.account

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fulora.online.R
import com.fulora.preference.getUserEmail
import com.fulora.preference.getUserName
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

private val TAG = AccountViewModel::class.java.name
/**
 *
 */
class AccountViewModel(context: Context) : ViewModel() {

    private var _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private var _email = MutableLiveData<String>()

    init {
        _name.value = getUserName(context)
        _email.value = getUserEmail(context)
    }

    fun save(fragment: AccountFragment, name: String, oldPassword: String, newPassword: String, confirmPassword: String) {
        val user = Firebase.auth.currentUser

        if (!oldPassword.isNullOrEmpty() && !newPassword.isNullOrEmpty() && !confirmPassword.isNullOrEmpty()) {
            val auth = Firebase.auth

            auth.signInWithEmailAndPassword(_email.value!!, oldPassword)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInUser:success")
                        if (newPassword == confirmPassword) {
                            user!!.updatePassword(newPassword)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "User password updated.")
                                    }
                                }
                        } else {
                            Snackbar.make(
                                fragment.requireView(),
                                fragment.getString(R.string.new_password_didnt_match_confirm_password),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInUser:failure", it.exception)
                        Snackbar.make(
                            fragment.requireView(),
                            fragment.getString(R.string.password_invalid),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
        }

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }
    }
}