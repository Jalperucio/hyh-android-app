package com.jalper.myfirstapp.lessonsapp.model

sealed class Language(var isSelected: Boolean = true) {
    object Android : Language()
    object IOS : Language()
    object Flutter : Language()
}