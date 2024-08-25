package com.hilguener.marvelsuperheroes.domain.model.creators

data class CreatorDataWrapper(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: CreatorDataContainer,
    val etag: String,
    val status: String
)