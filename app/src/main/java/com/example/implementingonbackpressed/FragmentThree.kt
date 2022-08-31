package com.example.implementingonbackpressed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.implementingonbackpressed.databinding.FragmentThreeBinding

class FragmentThree : Fragment() {

    lateinit var binding: FragmentThreeBinding

    /**
     * Callback for Handling back pressed
     * if switch not enabled, move to fragment two.
     */
    private val callbackOne = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().navigateUp()
        }
    }

    /**
     * Callback for Handling back pressed
     * if switch enabled, move to fragment one.
     */
    private val callbackTwo = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            findNavController().navigate(R.id.action_fragmentThree_to_fragmentOne)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater)
        val dispatcher = requireActivity().onBackPressedDispatcher
        dispatcher.addCallback(viewLifecycleOwner, callbackOne)
        dispatcher.addCallback(viewLifecycleOwner, callbackTwo)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            callbackTwo.isEnabled = isChecked
        }
    }
}