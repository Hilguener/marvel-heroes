package com.hilguener.marvelsuperheroes.domain.model.stories

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int,
)
