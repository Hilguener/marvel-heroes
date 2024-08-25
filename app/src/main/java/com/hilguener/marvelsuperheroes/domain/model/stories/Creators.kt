package com.hilguener.marvelsuperheroes.domain.model.stories

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)