package com.hilguener.marvelsuperheroes.domain.model.comic

data class ComicsDataWrapper(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: ComicsDataContainer,
    val etag: String,
    val status: String
)