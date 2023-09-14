package com.example.rickandmorty.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.viewmodel.CharactersViewModel

class CharacterDetailFragment : Fragment() {

    private val binding: FragmentCharacterDetailBinding by lazy {
        FragmentCharacterDetailBinding.inflate(layoutInflater)
    }

    private lateinit var charactersViewModel: CharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initUI()
    }

    private fun initViewModel() {
        charactersViewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
    }

    private fun initUI() {
        val character = charactersViewModel.selectedCharacter

        if (character != null) {
            binding.tvCharacterDetailName.text = character.name
            binding.tvCharacterDetailSpecie.text = character.species.name

            Glide.with(requireContext())
                .load(character.image)
                .into(binding.ivCharacterDetail)
        }
    }
}