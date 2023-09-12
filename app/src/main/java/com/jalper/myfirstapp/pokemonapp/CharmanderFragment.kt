package com.jalper.myfirstapp.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jalper.myfirstapp.databinding.FragmentCharmanderBinding
import com.jalper.myfirstapp.databinding.FragmentTabBinding

class CharmanderFragment : Fragment() {

    private lateinit var binding: FragmentCharmanderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharmanderBinding.inflate(layoutInflater)
        return binding.root
    }
}