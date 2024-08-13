package com.hilguener.marvelsuperheroes.domain.use_case.state

import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic

data class CharactersState(
    val characters: List<Character> = emptyList(),
    val comics: List<Comic> = emptyList(),
    val isLoadingComics: Boolean = false,
    val error: String? = null
)