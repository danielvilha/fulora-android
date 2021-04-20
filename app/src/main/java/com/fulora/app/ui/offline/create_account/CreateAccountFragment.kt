package com.fulora.app.ui.offline.create_account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fulora.app.R
import com.fulora.app.databinding.FragmentCreateAccountBinding
import com.fulora.app.di.Injector
import com.fulora.app.isValidEmail
import com.fulora.app.isValidPassword
import com.fulora.app.ui.offline.enums.Account

/**
 * A [Fragment] subclass.
 *
 * This fragment is to user create account.
 */
class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        // Get the viewModel
        viewModel = Injector.provideCreateAccountViewModel(this)

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
            viewModel.toSignIn()
        }

        return binding.root
    }

    // Function create account
    private fun createAccount(name: String, email: String, password: String, type: Account) {

        when {
            name.isEmpty() && type == Account.BY_EMAIL -> binding.nomeEditText.error = getString(R.string.error_name)
            !email.isValidEmail() && type == Account.BY_EMAIL -> binding.emailEditText.error = getString(R.string.error_email)
            !password.isValidPassword() && type == Account.BY_EMAIL -> binding.passwordEditText.error = getString(R.string.error_password)
            else -> viewModel.createAccount(name, email, password, type)
        }
    }
}