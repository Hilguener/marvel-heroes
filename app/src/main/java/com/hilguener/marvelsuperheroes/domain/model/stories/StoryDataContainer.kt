package com.hilguener.marvelsuperheroes.domain.model.stories

data class StoryDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Story>,
    val total: Int
)