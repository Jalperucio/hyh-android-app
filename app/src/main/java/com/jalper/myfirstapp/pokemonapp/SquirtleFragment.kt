package com.jalper.myfirstapp.pokemonapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.FragmentSquirtleBinding
import com.jalper.myfirstapp.databinding.FragmentTabBinding

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

        /**Se puede añadir un placeholder por si una imagen no se
         * carga correctamente o es errónea, tenerla por defecto
         */
        val squirtleUrl = "https://en.wikipedia.org/wiki/Squirtle#/media/File:Pok%C3%A9mon_Squirtle_art.png"
        Glide.with(this)
            .load(squirtleUrl)
            .placeholder(R.drawable.ic_pokemon_missingno)
            .into(binding.ivSquirtleImage)
    }
}