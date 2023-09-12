package com.jalper.myfirstapp.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jalper.myfirstapp.databinding.FragmentBulbasaurBinding
import com.jalper.myfirstapp.databinding.FragmentTabBinding

class BulbasaurFragment : Fragment() {

    private lateinit var binding: FragmentBulbasaurBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBulbasaurBinding.inflate(layoutInflater)
        return binding.root
    }
}