package com.hilguener.marvelsuperheroes.domain.model.stories

data class StoryDataWrapper(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: StoryDataContainer,
    val etag: String,
    val status: String
)