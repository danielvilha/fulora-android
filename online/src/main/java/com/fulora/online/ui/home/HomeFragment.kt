package com.fulora.online.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.fulora.online.R
import com.fulora.online.databinding.FragmentHomeBinding


private var TAG = HomeFragment::class.java.name
/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)

        // Create viewModel
        viewModelFactory = HomeViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        binding.fab.setOnClickListener {
            viewModel.toRegister(this)
        }

        binding.emptyState.visibility = if (viewModel.emptyPlants.value != true) View.GONE else View.VISIBLE

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "START")
    }
}