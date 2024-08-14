package com.hilguener.marvelsuperheroes.domain.model.events

data class EventDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Event>,
    val total: Int
)