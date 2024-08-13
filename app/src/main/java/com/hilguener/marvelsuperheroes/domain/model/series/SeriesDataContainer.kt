package com.hilguener.marvelsuperheroes.domain.model.series

data class SeriesDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Series>,
    val total: Int
)