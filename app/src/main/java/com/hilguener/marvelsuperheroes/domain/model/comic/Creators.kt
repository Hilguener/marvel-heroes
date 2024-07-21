package com.hilguener.marvelsuperheroes.domain.model.comic

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)