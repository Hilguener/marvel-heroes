package com.hilguener.marvelsuperheroes.domain.usecase

import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.character.Character

class GetCharactersUseCase(private val httpRepository: HttpRepository) {
    suspend operator fun invoke(haveError: suspend (String) -> Unit): Character? {
        var characterList: Character? = null
        val response = httpRepository.getCharacters()

        if (response.isSuccessful) {
            characterList = response.body()?.data?.results?.firstOrNull()
        } else {
            val errorMsg = response.message()
            haveError(errorMsg)
        }
        return characterList
    }
}
