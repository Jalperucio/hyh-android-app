package com.jalper.myfirstapp.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.FragmentPikachuBinding

class PikachuFragment : Fragment() {

    private lateinit var binding: FragmentPikachuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPikachuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Se puede añadir un placeholder por si una imagen no se
         * carga correctamente o es errónea, tenerla por defecto
         */
        val pikachuWrongUrl = "https://www.fake.com/pikachu_fake.png"

        Glide.with(this)
            .load(pikachuWrongUrl)
            .placeholder(R.drawable.ic_pokemon_missingno)
            .into(binding.ivPikachuImage)
    }
}