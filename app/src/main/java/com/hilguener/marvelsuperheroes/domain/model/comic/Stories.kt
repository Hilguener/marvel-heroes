package com.hilguener.marvelsuperheroes.domain.model.comic

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)