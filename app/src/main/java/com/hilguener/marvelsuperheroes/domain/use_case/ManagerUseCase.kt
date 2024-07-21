package com.hilguener.marvelsuperheroes.domain.use_case

data class ManagerUseCase(
    val getCharactersUseCase: GetCharactersUseCase,
    val getCharactersComicsById: GetCharactersComicsById
)
