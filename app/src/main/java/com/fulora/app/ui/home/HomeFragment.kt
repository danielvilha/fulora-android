package com.fulora.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fulora.app.R
import com.fulora.app.databinding.FragmentHomeBinding
import com.fulora.app.di.Injector

/**
 * A [Fragment] subclass.
 * This is the HomeFragment, for the person to see and create new items.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_home.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).supportActionBar!!.show()

        val application = requireNotNull(this.activity).application
        viewModel = Injector.provideHomeViewModel(requireContext())

        // I created this instance of the ViewModel Factory.
//        val dataSource = FuloraDatabase.getInstance(application).fuloraDatabaseDao
//        val viewModelFactory = HomeViewModelFactory(dataSource, application)
//
//        // Get a reference to the ViewModel associated with this fragment.
//        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
//
//        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}