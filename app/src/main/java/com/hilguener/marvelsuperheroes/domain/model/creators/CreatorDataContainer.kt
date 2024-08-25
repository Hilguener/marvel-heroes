package com.hilguener.marvelsuperheroes.domain.model.creators

data class CreatorDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Creator>,
    val total: Int
)