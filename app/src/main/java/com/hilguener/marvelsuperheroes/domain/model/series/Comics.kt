package com.hilguener.marvelsuperheroes.domain.model.series

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)