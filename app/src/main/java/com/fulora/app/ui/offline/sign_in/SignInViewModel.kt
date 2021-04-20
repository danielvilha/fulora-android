package com.fulora.app.ui.offline.sign_in

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.fulora.app.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
class SignInViewModel(private val fragment: SignInFragment) : ViewModel() {

    // To sign out a user, call signOut: Firebase.auth.signOut()
    private lateinit var auth: FirebaseAuth

    // Function to sign in in application
    fun signIn(email: String, password: String) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Snackbar.make(
                        fragment.requireView(),
                        fragment.getString(R.string.user_or_password_invalid),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun createAccount() {
        fragment.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToCreateAccountFragment())
    }

    private fun updateUI(user: FirebaseUser?) {
        val sharedPreferences: SharedPreferences = fragment.requireContext().getSharedPreferences("USER", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("USER_NAME", user?.displayName)
        edit.putString("USER_EMAIL", user?.email)
        edit.putString("USER_UID", user?.uid)
        edit.apply()


        fragment.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
    }
}