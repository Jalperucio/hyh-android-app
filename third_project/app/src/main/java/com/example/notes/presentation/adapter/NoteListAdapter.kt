package com.example.notes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.RowNoteListItemBinding
import com.example.notes.model.Note

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {

    var noteList: ArrayList<Note> = ArrayList()

    var onClickListener: (Note) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = RowNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = noteList[position]

        holder.titleTextView.text = note.title
        holder.descriptionTextView.text = note.description

        holder.rootView.setOnClickListener {
            onClickListener.invoke(note)
        }

        holder.favoriteImageView.setImageResource(if (note.isFavorite) {
            R.drawable.baseline_favorite_24
        } else {
            R.drawable.baseline_favorite_border_24
        })
    }

    fun submitList(list: List<Note>) {
        noteList.clear()
        noteList.addAll(list)
        notifyDataSetChanged()
    }

    inner class NoteListViewHolder(binding: RowNoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val titleTextView = binding.tvNoteItemTitle
        val descriptionTextView = binding.tvNoteItemDescription
        val favoriteImageView = binding.ivNoteItemFavorite
    }

}