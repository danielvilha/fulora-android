package com.fulora.offline.ui.login

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
import com.google.firebase.ktx.Firebase

val TAG = LoginViewModel::class.simpleName
/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 */
class LoginViewModel : ViewModel() {

    // To sign out a user, call signOut: Firebase.auth.signOut()
    private lateinit var auth: FirebaseAuth

    // Function to sign in in application
    fun signIn(fragment: LoginFragment, email: String, password: String, type: Account) {
        // Call function to hide keyboard
        fragment.requireView().hideKeyboard()

        when (type) {
            Account.BY_GOOGLE -> signInByGoogle(fragment)
            Account.BY_FACEBOOK -> signInByFacebook(fragment)
            else -> signInByMail(fragment, email, password)
        }
    }

    fun toCreateAccount(fragment: LoginFragment) {
        fragment.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment())
    }

    private fun signInByGoogle(fragment: LoginFragment) {
        Snackbar.make(fragment.requireView(), fragment.getString(
            R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun signInByFacebook(fragment: LoginFragment) {
        Snackbar.make(fragment.requireView(), fragment.getString(
            R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun signInByMail(fragment: LoginFragment, email: String, password: String) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInUser:success")
                    val user = auth.currentUser
                    insertUser(fragment.requireContext(), user?.displayName, user?.email, user?.uid)

                    updateUI(fragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInUser:failure", it.exception)
                    Snackbar.make(
                        fragment.requireView(),
                        fragment.getString(R.string.user_or_password_invalid),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun updateUI(fragment: LoginFragment) {
        fragment.activity?.startActivity(Intent(fragment.requireContext(), OnlineActivity::class.java))
    }
}