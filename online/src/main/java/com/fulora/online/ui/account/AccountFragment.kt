package com.fulora.online.ui.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fulora.online.R
import com.fulora.online.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModelFactory: AccountViewModelFactory
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        // Create viewModel
        viewModelFactory = AccountViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(AccountViewModel::class.java)

        return binding.root
    }
}