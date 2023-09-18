package com.example.rickandmorty.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.RowCharacterItemBinding
import com.example.rickandmorty.model.Character

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private var characterList: List<Character> = emptyList()

    var onClickListener: (Character) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = RowCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val item = characterList[position]

        holder.rootView.setOnClickListener {
            onClickListener.invoke(item)
        }

        holder.nameTextView.text = item.name

        Glide.with(holder.characterImageView)
            .load(item.image)
            .into(holder.characterImageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Character>) {
        characterList = list
        notifyDataSetChanged()
    }

    inner class CharacterListViewHolder(binding: RowCharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val nameTextView = binding.tvCharacterItemName
        val characterImageView = binding.ivCharacterItem
    }
}