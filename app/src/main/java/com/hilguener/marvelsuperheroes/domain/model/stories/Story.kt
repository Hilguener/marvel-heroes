package com.hilguener.marvelsuperheroes.domain.model.stories

data class Story(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val originalIssue: OriginalIssue,
    val resourceURI: String,
    val series: Series,
    val thumbnail: Any,
    val title: String,
    val type: String
)