package com.hilguener.marvelsuperheroes.domain.model.character

import com.google.gson.annotations.SerializedName

data class ComicItem(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)