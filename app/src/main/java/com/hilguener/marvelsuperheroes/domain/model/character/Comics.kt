package com.hilguener.marvelsuperheroes.domain.model.character

import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ComicItem>,
    @SerializedName("returned") val returned: Int,
)
