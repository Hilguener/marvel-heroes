package com.hilguener.marvelsuperheroes.domain.model.events

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int,
)
