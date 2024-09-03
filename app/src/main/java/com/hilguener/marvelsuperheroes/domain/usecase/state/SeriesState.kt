package com.hilguener.marvelsuperheroes.domain.usecase.state

import com.hilguener.marvelsuperheroes.domain.model.series.Series

data class SeriesState(
    val series: List<Series> = emptyList(),
    val isLoadingSeries: Boolean = false,
    val error: String? = null,
)
