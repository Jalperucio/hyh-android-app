package com.example.notes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteDetailBinding
import com.example.notes.model.Note
import com.example.notes.model.ResourceState
import com.example.notes.presentation.viewmodel.EditNoteState
import com.example.notes.presentation.viewmodel.NoteDetailState
import com.example.notes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class EditNoteFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by activityViewModel()
    private val args: EditNoteFragmentArgs by navArgs()

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            editNote()
        }
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
        notesViewModel.noteDetailLiveData.observe(viewLifecycleOwner) { state ->
            handleNoteDetailState(state)
        }

        notesViewModel.editNoteLiveData.observe(viewLifecycleOwner) { state ->
            handleEditNoteState(state)
        }

        notesViewModel.fetchNote(args.noteId)
    }

    private fun handleEditNoteState(state: EditNoteState) {
        when(state) {
            is ResourceState.Loading -> {
                //
            }
            is ResourceState.Success -> {
                findNavController().popBackStack()
            }
            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun handleNoteDetailState(state: NoteDetailState) {
        when(state) {
            is ResourceState.Loading -> {
                //
            }
            is ResourceState.Success -> {
                note = state.result
                initUI(state.result)
            }
            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun initUI(note: Note) {
        binding.tieNoteDetailTitle.setText(note.title)
        binding.tieNoteDetailDescription.setText(note.description)

        binding.ibNoteDetailFavorite.setImageResource(
            if (note.isFavorite) {
                R.drawable.baseline_favorite_24
            } else {
                R.drawable.baseline_favorite_border_24
            }
        )
    }

    private fun editNote() {
        val title = binding.tieNoteDetailTitle.text.toString()
        val description = binding.tieNoteDetailDescription.text.toString()
        val date = System.currentTimeMillis()

        if (title.isNotBlank() && description.isNotBlank() && note != null) {
            val editedNote = Note(
                id = note!!.id,
                title = title,
                description = description,
                date = date,
                isFavorite = note!!.isFavorite
            )

            notesViewModel.editNote(editedNote)
        }
    }

}