package com.hilguener.marvelsuperheroes.domain.model.character

data class CharactersDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: CharactersDataContainer,
)
