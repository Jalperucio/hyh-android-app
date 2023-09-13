package com.jalper.myfirstapp.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.FragmentSquirtleBinding

class SquirtleFragment : Fragment() {

    private lateinit var binding: FragmentSquirtleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSquirtleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Insertar imagen programaticamente*/
        binding.ivSquirtleImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.ic_pokemon_squirtle))
    }
}