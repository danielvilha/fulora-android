package com.fulora.app.ui.offline.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fulora.app.R
import com.fulora.app.databinding.FragmentSignInBinding
import com.fulora.app.di.Injector
import com.fulora.app.isValidPassword
import com.fulora.app.ui.offline.enums.Account

/**
 * A [Fragment] subclass.
 */
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        // Get the viewModel
        viewModel = Injector.provideSignInViewModel(this)

        binding.buttonAccessAccount.setOnClickListener {
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(), Account.BY_EMAIL)
        }

        binding.buttonAccessAccountGoogle.setOnClickListener {
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(), Account.BY_GOOGLE)
        }

        binding.buttonAccessAccountFacebook.setOnClickListener {
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(), Account.BY_FACEBOOK)
        }

        binding.textCreateAccount.setOnClickListener {
            viewModel.createAccount()
        }

        return binding.root
    }

    private fun signIn(email: String, password: String, type: Account) {
        when {
            email.isBlank() && type == Account.BY_EMAIL -> binding.emailEditText.error = getString(R.string.error_email)
            !password.isValidPassword() && type == Account.BY_EMAIL -> binding.passwordEditText.error = getString(R.string.error_password)
            else -> viewModel.signIn(email, password, type)
        }
    }
}