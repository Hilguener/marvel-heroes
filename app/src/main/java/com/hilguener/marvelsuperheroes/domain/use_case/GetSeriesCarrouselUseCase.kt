package com.hilguener.marvelsuperheroes.domain.use_case

import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.series.Series

class GetSeriesCarrouselUseCase(private val repository: HttpRepository) {
    suspend operator fun invoke(haveError: suspend (String) -> Unit): List<Series>? {
        return try {
            val response = repository.getSeriesCarrousel()
            if (response.isSuccessful) {
                response.body()?.data?.results
            } else {
                val errorMsg = response.message()
                haveError(errorMsg)
                null
            }
        } catch (e: Exception) {
            haveError("Exception: ${e.message}")
            null
        }
    }
}