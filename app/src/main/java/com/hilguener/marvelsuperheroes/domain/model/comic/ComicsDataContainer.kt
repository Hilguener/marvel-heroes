package com.hilguener.marvelsuperheroes.domain.model.comic

data class ComicsDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Comic>,
    val total: Int
)