package com.hilguener.marvelsuperheroes.domain.model.events

data class EventDataWrapper(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: EventDataContainer,
    val etag: String,
    val status: String
)