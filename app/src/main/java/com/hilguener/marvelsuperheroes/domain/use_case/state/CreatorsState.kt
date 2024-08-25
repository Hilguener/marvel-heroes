package com.hilguener.marvelsuperheroes.domain.use_case.state

import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.domain.model.creators.Creator

data class CreatorsState(
    val characters: List<Creator> = emptyList(),
    val comics: List<Comic> = emptyList(),
    val isLoadingComics: Boolean = false,
    val error: String? = null
)
