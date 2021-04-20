package com.fulora.app.ui.offline.sign_in

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.fulora.app.R
import com.fulora.app.hideKeyboard
import com.fulora.app.ui.offline.enums.Account
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

val TAG = SignInViewModel::class.simpleName
/**
 * Created by danielvilha on 19/04/21
 * https://github.com/danielvilha
 */
class SignInViewModel(private val fragment: SignInFragment) : ViewModel() {

    // To sign out a user, call signOut: Firebase.auth.signOut()
    private lateinit var auth: FirebaseAuth

    // Function to sign in in application
    fun signIn(email: String, password: String, type: Account) {
        // Call function to hide keyboard
        fragment.requireView().hideKeyboard()

        when (type) {
            Account.BY_GOOGLE -> signInByGoogle()
            Account.BY_FACEBOOK -> signInByFacebook()
            else -> signInByMail(email, password)
        }
    }

    fun createAccount() {
        fragment.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToCreateAccountFragment())
    }

    private fun signInByGoogle() {
        Snackbar.make(fragment.requireView(), fragment.getString(R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun signInByFacebook() {
        Snackbar.make(fragment.requireView(), fragment.getString(R.string.requisition_not_created), Snackbar.LENGTH_LONG).show()
    }

    private fun signInByMail(email: String, password: String) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInUser:success")
                    val user = auth.currentUser
                    updateUI(user)
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

    private fun updateUI(user: FirebaseUser?) {
        val sharedPreferences: SharedPreferences =
            fragment.requireContext()
                .getSharedPreferences("FuloraSharedPref", MODE_PRIVATE)

        val edit = sharedPreferences.edit()
        edit.putString("USER_NAME", user?.displayName)
        edit.putString("USER_EMAIL", user?.email)
        edit.putString("USER_UID", user?.uid)
        edit.apply()

        fragment.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
    }
}