package com.hilguener.marvelsuperheroes.domain.use_case

import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.events.Event

class GetEventsCarrouselUseCase(private val repository: HttpRepository) {
    suspend operator fun invoke(haveError: suspend (String) -> Unit): List<Event>? {
        return try {
            val response = repository.getEventsCarrousel()
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