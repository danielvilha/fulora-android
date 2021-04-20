package com.fulora.app.ui.offline.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fulora.app.R
import com.fulora.app.databinding.FragmentSignInBinding
import com.fulora.app.di.Injector

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

        viewModel = Injector.provideSignInViewModel(this)

        binding.buttonAccessAccount.setOnClickListener {
            viewModel.signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        }

        binding.textCreateAccount.setOnClickListener {
            viewModel.createAccount()
        }

        return binding.root
    }


}