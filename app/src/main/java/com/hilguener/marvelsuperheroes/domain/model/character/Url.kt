package com.hilguener.marvelsuperheroes.domain.model.character

import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)