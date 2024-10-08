package com.hilguener.marvelsuperheroes.domain.usecase

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.data.paging.CharacterPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.character.Character

class GetCharactersUseCase(private val httpRepository: HttpRepository) {
    fun getPagingSource(query: String? = null): PagingSource<Int, Character> {
        return CharacterPagingSource(httpRepository, query)
    }
}
