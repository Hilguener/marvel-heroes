package com.hilguener.marvelsuperheroes.domain.model.character

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)