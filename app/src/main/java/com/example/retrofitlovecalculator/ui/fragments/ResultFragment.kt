package com.example.retrofitlovecalculator.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.retrofitlovecalculator.R
import com.example.retrofitlovecalculator.data.CalculatedResult
import com.example.retrofitlovecalculator.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    private lateinit var calculatedResult: CalculatedResult
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculatedResult = ResultFragmentArgs.fromBundle(requireArguments()).resultModel
        displayResult()
    }

    private fun displayResult() {
        binding.tvFirstName.text = calculatedResult.firstName
        binding.tvSecondName.text = calculatedResult.secondName
        binding.tvPercent.text = "Compatibility: ${calculatedResult.percentage}%"
        binding.tvResult.text = calculatedResult.result

        binding.btnTryAgain.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}