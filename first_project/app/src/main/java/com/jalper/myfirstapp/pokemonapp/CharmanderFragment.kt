package com.jalper.myfirstapp.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.FragmentCharmanderBinding

class CharmanderFragment : Fragment() {

    private lateinit var binding: FragmentCharmanderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharmanderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Insertar imagen por Glide*/
        Glide.with(this)
            .load("https://images.wikidexcdn.net/mwuploads/wikidex/5/56/latest/20200307023245/Charmander.png")
            .placeholder(R.drawable.ic_pokemon_missingno)
            .into(binding.ivCharmanderImage)
    }
}