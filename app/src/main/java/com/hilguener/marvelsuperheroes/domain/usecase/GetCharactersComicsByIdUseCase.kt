package com.hilguener.marvelsuperheroes.domain.usecase

import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic

class GetCharactersComicsByIdUseCase(private val repository: HttpRepository) {
    suspend operator fun invoke(
        characterId: Int,
        haveError: suspend (String) -> Unit,
    ): List<Comic>? {
        return try {
            val response = repository.getCharacterComicsById(characterId)
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
