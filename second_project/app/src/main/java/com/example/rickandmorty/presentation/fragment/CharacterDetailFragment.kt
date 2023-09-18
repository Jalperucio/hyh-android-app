package com.example.rickandmorty.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ResourceState
import com.example.rickandmorty.presentation.viewmodel.CharacterDetailState
import com.example.rickandmorty.presentation.viewmodel.CharactersViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CharacterDetailFragment : Fragment() {

    private val binding: FragmentCharacterDetailBinding by lazy {
        FragmentCharacterDetailBinding.inflate(layoutInflater)
    }

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val charactersViewModel: CharactersViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        charactersViewModel.fetchCharacter(args.characterId)
    }

    private fun initViewModel() {
        charactersViewModel.getCharacterDetailLiveData().observe(viewLifecycleOwner) { state ->
            handleCharacterDetailState(state)
        }
    }

    private fun handleCharacterDetailState(state: CharacterDetailState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbCjaracterDetail.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbCjaracterDetail.visibility = View.GONE
                initUI(state.result)
            }

            is ResourceState.Error -> {
                binding.pbCjaracterDetail.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI(character: Character) {
        binding.tvCharacterDetailName.text = character.name
        binding.tvCharacterDetailSpecie.text = character.species.name

        Glide.with(requireContext())
            .load(character.image)
            .into(binding.ivCharacterDetail)
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.error)
            .setMessage(error)
            .setPositiveButton(R.string.action_ok, null)
            .setNegativeButton(R.string.action_retry) { dialog, witch ->
                charactersViewModel.fetchCharacters()
            }
            .show()
    }
}