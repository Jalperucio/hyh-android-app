package com.jalper.myfirstapp.lessonsapp

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ItemLanguageBinding
import com.jalper.myfirstapp.lessonsapp.model.Language

class LanguagesViewHolder(
    private val binding: ItemLanguageBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun render(language: Language) {
        binding.tvItemLanguageName

        val name: Int

        when (language) {
            Language.Android -> name = R.string.language_android
            Language.IOS -> name = R.string.language_ios
            Language.Flutter -> name = R.string.language_flutter
        }

        binding.root.setCardBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                if (language.isSelected) R.color.lesson_card_background
                else R.color.lesson_card_background_disabled
            )
        )
        //Mismo método que las líneas superiores
//        if(language.isSelected)  binding.root.setCardBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.lesson_card_background))
//        else  binding.root.setCardBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.lesson_card_background_disabled))

        binding.tvItemLanguageName.text = binding.root.context.getString(name)
    }
}
