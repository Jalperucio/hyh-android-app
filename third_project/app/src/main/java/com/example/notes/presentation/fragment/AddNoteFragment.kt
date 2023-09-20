package com.example.notes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentNoteDetailBinding
import com.example.notes.model.Note
import com.example.notes.model.ResourceState
import com.example.notes.presentation.viewmodel.AddNoteState
import com.example.notes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AddNoteFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*requireActivity().onBackPressedDispatcher.addCallback(this) {
            saveNote()
        }*/

        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                saveNote()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        notesViewModel.addNoteLiveData.observe(viewLifecycleOwner) { state ->
            handleNoteListState(state)
        }
    }

    private fun handleNoteListState(state: AddNoteState) {
        when(state) {
            is ResourceState.Loading -> {
                //
            }
            is ResourceState.Success -> {
                findNavController().popBackStack()
            }
            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    private fun saveNote() {
        val title = binding.tieNoteDetailTitle.text.toString()
        val description = binding.tieNoteDetailDescription.text.toString()
        val date = System.currentTimeMillis()

        if (title.isNotBlank() && description.isNotBlank()) {
            notesViewModel.addNote(
                Note(
                    title = title,
                    description = description,
                    date = date
                )
            )
        } else {
            findNavController().popBackStack()
        }
    }

}