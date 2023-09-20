package com.example.notes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteListBinding
import com.example.notes.model.ResourceState
import com.example.notes.presentation.adapter.NoteListAdapter
import com.example.notes.presentation.viewmodel.DeleteNoteState
import com.example.notes.presentation.viewmodel.NoteListState
import com.example.notes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    private val noteListAdapter = NoteListAdapter()

    private val notesViewModel: NotesViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteListAdapter.onClickListener = { note ->
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToEditNoteFragment(note.id)
            )
        }

        binding.fabNoteListAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvNoteList.adapter = noteListAdapter
        binding.rvNoteList.layoutManager = LinearLayoutManager(requireContext())

        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = noteListAdapter.noteList[position]

                noteListAdapter.noteList.removeAt(position)
                noteListAdapter.notifyItemRemoved(position)

                notesViewModel.deleteNote(note.id)
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.rvNoteList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        notesViewModel.noteListLiveData.observe(viewLifecycleOwner) { state ->
            handleNoteListState(state)
        }

        notesViewModel.deleteNoteLiveData.observe(viewLifecycleOwner) { state ->
            handleRemoveNoteState(state)
        }

        notesViewModel.fetchNoteList()
    }

    private fun handleRemoveNoteState(state: DeleteNoteState) {
        when(state) {
            is ResourceState.Loading -> {
                //
            }
            is ResourceState.Success -> {
                Toast.makeText(requireContext(), "La nota se ha eliminado correctamente", Toast.LENGTH_SHORT).show()
            }
            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    private fun handleNoteListState(state: NoteListState) {
        when(state) {
            is ResourceState.Loading -> {
                //
            }
            is ResourceState.Success -> {
                noteListAdapter.submitList(state.result)
            }
            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

}