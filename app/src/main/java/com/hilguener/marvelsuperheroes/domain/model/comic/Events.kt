package com.hilguener.marvelsuperheroes.domain.model.comic

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)