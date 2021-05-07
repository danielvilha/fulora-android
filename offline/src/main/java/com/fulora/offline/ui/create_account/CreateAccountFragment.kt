package com.fulora.offline.ui.create_account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fulora.offline.R
import com.fulora.offline.databinding.FragmentCreateAccountBinding
import com.fulora.offline.isValidEmail
import com.fulora.offline.isValidPassword
import com.fulora.offline.ui.login.LoginViewModel
import com.fulora.offline.ui.login.LoginViewModelFactory
import com.fulora.offline.ui.login.enums.Account

/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 *
 * This [Fragment] subclass is to user create account.
 */
class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var viewModelFactory: CreateAccountViewModelFactory
    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)

        // Create viewModel
        viewModelFactory = CreateAccountViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreateAccountViewModel::class.java)

        binding.buttonCreateAccount.setOnClickListener {
            createAccount(binding.nomeEditText.text.toString(), binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(), Account.BY_EMAIL)
        }

        binding.buttonCreateAccountGoogle.setOnClickListener {
            createAccount(binding.nomeEditText.text.toString(), binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(), Account.BY_GOOGLE)
        }

        binding.buttonCreateAccountFacebook.setOnClickListener {
            createAccount(binding.nomeEditText.text.toString(), binding.emailEditText.text.toString(), binding.passwordEditText.text.toString(), Account.BY_FACEBOOK)
        }

        binding.textSignIn.setOnClickListener {
            viewModel.toSignIn(this)
        }

        return binding.root
    }

    // Function create account
    private fun createAccount(name: String, email: String, password: String, type: Account) {

        when {
            name.isEmpty() && type == Account.BY_EMAIL -> binding.nomeEditText.error = getString(R.string.error_name)
            !email.isValidEmail() && type == Account.BY_EMAIL -> binding.emailEditText.error = getString(R.string.error_email)
            !password.isValidPassword() && type == Account.BY_EMAIL -> binding.passwordEditText.error = getString(R.string.error_password)
            else -> viewModel.createAccount(this, name, email, password, type)
        }
    }
}