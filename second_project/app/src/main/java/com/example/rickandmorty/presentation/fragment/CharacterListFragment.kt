package com.example.rickandmorty.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.data.character.CharactersDataImpl
import com.example.rickandmorty.presentation.adapter.CharacterListAdapter
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.model.ResourceState
import com.example.rickandmorty.presentation.viewmodel.CharacterListState
import com.example.rickandmorty.presentation.viewmodel.CharactersViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CharacterListFragment : Fragment() {

    private val binding: FragmentCharacterListBinding by lazy {
        FragmentCharacterListBinding.inflate(layoutInflater)
    }

    private val characterListAdapter = CharacterListAdapter()

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

        charactersViewModel.fetchCharacters()
    }

    private fun initViewModel() {
        charactersViewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]

        charactersViewModel.getCharacterLiveData().observe(viewLifecycleOwner) { state ->
            handleCharacterListState(state)
        }
    }

    private fun handleCharacterListState(state: CharacterListState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbCharacterList.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbCharacterList.visibility = View.GONE
                characterListAdapter.submitList(state.result)
            }

            is ResourceState.Error -> {
                binding.pbCharacterList.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI() {
        binding.rvCharacterList.adapter = characterListAdapter
        binding.rvCharacterList.layoutManager = LinearLayoutManager(requireContext())

        characterListAdapter.onClickListener = { character ->
            findNavController().navigate(
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    character.id.toInt()
                )
            )
        }
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