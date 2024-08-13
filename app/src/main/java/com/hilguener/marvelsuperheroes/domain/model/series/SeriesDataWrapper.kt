package com.hilguener.marvelsuperheroes.domain.model.series

data class SeriesDataWrapper(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: SeriesDataContainer,
    val etag: String,
    val status: String
)