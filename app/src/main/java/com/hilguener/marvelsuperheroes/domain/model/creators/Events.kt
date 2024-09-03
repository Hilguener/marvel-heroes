package com.hilguener.marvelsuperheroes.domain.model.creators

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int,
)
