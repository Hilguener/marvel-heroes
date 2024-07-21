package com.hilguener.marvelsuperheroes.domain.use_case

import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic

class GetCharactersUseCase(private val httpRepository: HttpRepository) {
    suspend operator fun invoke(haveError: suspend (String) -> Unit): List<Character>? {
        var characterList: List<Character>? = null
        val response = httpRepository.getCharacters()

        if (response.isSuccessful) {
            characterList = response.body()?.data?.results
        } else {
            val errorMsg = response.message()
            haveError(errorMsg)
        }
        return characterList
    }
}

