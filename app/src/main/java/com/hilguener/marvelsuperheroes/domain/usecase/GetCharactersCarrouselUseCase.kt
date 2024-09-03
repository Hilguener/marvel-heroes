package com.hilguener.marvelsuperheroes.domain.usecase

import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.character.Character

class GetCharactersCarrouselUseCase(private val repository: HttpRepository) {
    suspend operator fun invoke(haveError: suspend (String) -> Unit): List<Character>? {
        return try {
            val response = repository.getCharactersCarrousel()
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
