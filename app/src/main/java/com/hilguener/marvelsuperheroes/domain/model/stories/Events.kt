package com.hilguener.marvelsuperheroes.domain.model.stories

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)