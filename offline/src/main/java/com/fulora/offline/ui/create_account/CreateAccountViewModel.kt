package com.fulora.offline.ui.create_account

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.fulora.offline.R
import com.fulora.offline.hideKeyboard
import com.fulora.offline.ui.login.enums.Account
import com.fulora.online.OnlineActivity
import com.fulora.preference.insertUser
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

val TAG = CreateAccountViewModel::class.simpleName
/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
class CreateAccountViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    fun createAccount(fragment: CreateAccountFragment, name: String, email: String, password: String, type: Account) {
        // Call function to hide keyboard
        fragment.requireView().hideKeyboard()

        when (type) {
            Account.BY_GOOGLE -> {
                createAccountByGoogle(fragment)
            }
            Account.BY_FACEBOOK -> {
                createAccountByFacebook(fragment)
            }
            else -> {
                createAccountByEmail(fragment, name, email, password)
            }
        }
    }

    fun toSignIn(fragment: CreateAccountFragment) {
        fragment.findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment())
    }

    private fun createAccountByGoogle(fragment: CreateAccountFragment) {
        Snackbar.make(fragment.requireView(), fragment.getString(R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun createAccountByFacebook(fragment: CreateAccountFragment) {
        Snackbar.make(fragment.requireView(), fragment.getString(R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun createAccountByEmail(fragment: CreateAccountFragment, name: String, email: String, password: String) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    updateUI(fragment, name)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                    Snackbar.make(fragment.requireView(), it.exception!!.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
    }

    private fun updateUI(fragment: CreateAccountFragment, name: String) {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated: $user")
                    insertUser(fragment.requireContext(), user.displayName, user.email, user.uid)

                    fragment.activity?.startActivity(Intent(fragment.requireContext(), OnlineActivity::class.java))
                } else {
                    Log.d(TAG, "User updated error: $task")
                }
            }
    }
}