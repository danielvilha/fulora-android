package com.fulora.online.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.AdapterViewBindingAdapter
import com.fulora.online.R
import com.fulora.online.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModelFactory: RegisterViewModelFactory
    private lateinit var viewModel: RegisterViewModel
    private var dateTime: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        // Create viewModel
        viewModelFactory = RegisterViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_remember_me,
            R.layout.item_spinner
        ).also {
            // Specify the layout to use when the list of choices appears
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerTime.adapter = it
        }

        binding.buttonSave.setOnClickListener {
            savePlant(binding.editTextPlantName.text.toString(), dateTime)
        }

        binding.spinnerTime.onItemSelectedListener = object : AdapterViewBindingAdapter.OnItemSelected,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    1 -> dateTime = "9:00"
                    2 -> dateTime = "15:00"
                    3 -> dateTime = "20:00"
                    4 -> {
                        dateTime = ""
                        hourPicker()
                    }
                    else -> {
                        Snackbar.make(requireView(), getString(R.string.you_need_to_select_a_time), Snackbar.LENGTH_LONG).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        return binding.root
    }

    private fun hourPicker() {
        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText(getString(R.string.select_time))
                .build()

        picker.addOnPositiveButtonClickListener {
            dateTime = "${picker.hour}:${picker.minute}"
        }

        picker.addOnCancelListener {
            dateTime = ""
        }

        picker.show(childFragmentManager, "PICKER")
    }

    private fun savePlant(name: String, dateTime: String) {
        when {
            name.isEmpty() -> binding.editTextPlantName.error = getString(R.string.error_insert_plant_name)
            dateTime.isEmpty() -> Snackbar.make(requireView(), getString(R.string.error_spinner_plant_time), Snackbar.LENGTH_LONG).show()
            else -> viewModel.savePlant(this, name, R.drawable.ic_plant_5.toString(), dateTime)
        }
    }


}