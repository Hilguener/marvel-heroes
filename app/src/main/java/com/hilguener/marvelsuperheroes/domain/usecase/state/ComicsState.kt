package com.hilguener.marvelsuperheroes.domain.usecase.state

import com.hilguener.marvelsuperheroes.domain.model.character.Character

data class ComicsState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
