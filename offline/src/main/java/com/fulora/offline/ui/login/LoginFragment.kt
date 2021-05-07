package com.fulora.offline.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fulora.offline.R
import com.fulora.offline.databinding.FragmentLoginBinding
import com.fulora.offline.isValidPassword
import com.fulora.offline.ui.login.enums.Account

/**
 * Created by danielvilha on 27/04/21
 * https://github.com/danielvilha
 *
 * This [Fragment] is for the user to be able to login
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Create viewModel
        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.buttonAccessAccount.setOnClickListener {
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(),
                Account.BY_EMAIL
            )
        }

        binding.buttonAccessAccountGoogle.setOnClickListener {
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(),
                Account.BY_GOOGLE
            )
        }

        binding.buttonAccessAccountFacebook.setOnClickListener {
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(),
                Account.BY_FACEBOOK
            )
        }

        binding.textCreateAccount.setOnClickListener {
            viewModel.toCreateAccount(this)
        }

        return binding.root
    }

    private fun signIn(email: String, password: String, type: Account) {
        when {
            email.isBlank() && type == Account.BY_EMAIL -> binding.emailEditText.error = getString(R.string.error_email)
            !password.isValidPassword() && type == Account.BY_EMAIL -> binding.passwordEditText.error = getString(R.string.error_password)
            else -> viewModel.signIn(this, email, password, type)
        }
    }
}