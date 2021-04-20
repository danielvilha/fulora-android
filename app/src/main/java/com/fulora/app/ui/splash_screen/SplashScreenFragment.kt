package com.fulora.app.ui.splash_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fulora.app.R
import com.fulora.app.databinding.FragmentSplashScreenBinding
import com.fulora.app.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A [Fragment] subclass.
 * Fragment for the starting or title screen of the app
 */
class SplashScreenFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding
    lateinit var viewModel: SplashScreenViewModel
    
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)

        // Get a reference to the ViewModel associated with this fragment.
        viewModel = Injector.providerSplashScreenViewModel(this)

        viewModel.showScreen.observe(viewLifecycleOwner, {
            activityScope.launch {
                delay(1000L)
                if (it) {
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment())
                } else {
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToOnboardingFragment())
                }
            }
        })

        return binding.root
    }
}