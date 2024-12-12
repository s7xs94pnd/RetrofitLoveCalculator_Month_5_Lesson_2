package com.example.retrofitlovecalculator.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.retrofitlovecalculator.R
import com.example.retrofitlovecalculator.data.ApiConstants
import com.example.retrofitlovecalculator.data.CalculatedResult
import com.example.retrofitlovecalculator.data.RetrofitInstance
import com.example.retrofitlovecalculator.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() = with(binding) {
        btnCalculate.setOnClickListener {
            Log.d("Debug", "Button clicked")
            RetrofitInstance.api.getPercentage(
                firstName = etFirstName.text.toString(),
                secondName = etSecondName.text.toString(),
                key = ApiConstants.KEY,
                host = ApiConstants.HOST
            ).enqueue(object : Callback<CalculatedResult> {
                override fun onResponse(call: Call<CalculatedResult>, response: Response<CalculatedResult>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("Debug", "API request successful: ${response.body()}")
                        val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(response.body()!!)
                        binding.etFirstName.text.clear()
                        binding.etSecondName.text.clear()
                        findNavController().navigate(action)

                    } else {
                        Log.e("Error", "API response failed: ${response.code()}")
                        Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<CalculatedResult>, t: Throwable) {
                    Log.e("Error", "API request failed: ${t.localizedMessage}")
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
