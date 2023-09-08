package com.jalper.myfirstapp.lessonsapp.model

sealed class Language {
    object Android : Language()
    object IOS : Language()
    object Flutter : Language()
}