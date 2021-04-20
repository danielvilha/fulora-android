package com.fulora.app.ui.offline.create_account

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.fulora.app.R
import com.fulora.app.hideKeyboard
import com.fulora.app.ui.offline.enums.Account
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

val TAG = CreateAccountViewModel::class.simpleName
/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
class CreateAccountViewModel(private val fragment: CreateAccountFragment): ViewModel() {

    private lateinit var auth: FirebaseAuth

    fun createAccount(name: String, email: String, password: String, type: Account) {
        // Call function to hide keyboard
        fragment.requireView().hideKeyboard()

        when (type) {
            Account.BY_GOOGLE -> {
                createAccountByGoogle()
            }
            Account.BY_FACEBOOK -> {
                createAccountByFacebook()
            }
            else -> {
                createAccountByEmail(name, email, password)
            }
        }
    }

    private fun createAccountByGoogle() {
        Snackbar.make(fragment.requireView(), fragment.getString(R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun createAccountByFacebook() {
        Snackbar.make(fragment.requireView(), fragment.getString(R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun createAccountByEmail(name: String, email: String, password: String) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    updateUI(name)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                    Snackbar.make(fragment.requireView(), it.exception!!.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }

    }

    private fun updateUI(name: String) {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }

        val sharedPreferences: SharedPreferences =
            fragment.requireContext()
                .getSharedPreferences("FuloraSharedPref", Context.MODE_PRIVATE)

        // Creating an Editor object to edit(write to the file)
        val edit = sharedPreferences.edit()

        edit.putString("USER_NAME", user.displayName)
        edit.putString("USER_EMAIL", user.email)
        edit.putString("USER_UID", user.uid)
        edit.apply()

        fragment.findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToHomeFragment())
    }

    fun toSignIn() {
        fragment.findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToSignInFragment())
    }
}