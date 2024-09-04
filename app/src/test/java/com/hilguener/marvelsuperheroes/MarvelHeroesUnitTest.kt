package com.hilguener.marvelsuperheroes

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hilguener.marvelsuperheroes.data.paging.CharacterPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.character.Comics
import com.hilguener.marvelsuperheroes.domain.model.character.Events
import com.hilguener.marvelsuperheroes.domain.model.character.Series
import com.hilguener.marvelsuperheroes.domain.model.character.Stories
import com.hilguener.marvelsuperheroes.domain.model.character.Thumbnail
import com.hilguener.marvelsuperheroes.domain.model.character.Url
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MarvelHeroesUnitTest {
    private lateinit var httpRepository: HttpRepository
    private lateinit var pagingSource: CharacterPagingSource

    @Before
    fun setUp() {
        // Mockando o HttpRepository
        httpRepository = mockk()
    }

    @Test
    fun `load returns Error when on failure`() = runTest {
        // Mockando uma resposta de erro do HttpRepository
        val exception = RuntimeException("Failed to load data")
        coEvery { httpRepository.getCharacters(page = 1, name = null) } throws exception

        // Criando uma instância do CharacterPagingSource
        pagingSource = CharacterPagingSource(httpRepository, name = null)

        // Executando o método load e verificando o resultado
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = Constants.LIMIT,
                placeholdersEnabled = false
            )
        )

        // Verificando se o resultado é um erro
        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(exception, result.throwable)
    }

    @Test
    fun `getRefreshKey returns correct key`() {
        // Criando um estado de paginação simulado
        val state = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = listOf(
                        Character(
                            id = 1,
                            name = "Spider-Man",
                            description = "A hero with spider-like abilities.",
                            modified = "2023-01-01T00:00:00-0500",
                            thumbnail = Thumbnail(path = "path/to/spiderman", extension = "jpg"),
                            resourceURI = "http://gateway.marvel.com/v1/public/characters/1",
                            comics = Comics(
                                available = 10,
                                collectionURI = "http://gateway.marvel.com/v1/public/characters/1/comics",
                                items = emptyList(),
                                returned = 10
                            ),
                            series = Series(
                                available = 5,
                                collectionURI = "http://gateway.marvel.com/v1/public/characters/1/series",
                                items = emptyList(),
                                returned = 5
                            ),
                            stories = Stories(
                                available = 3,
                                collectionURI = "http://gateway.marvel.com/v1/public/characters/1/stories",
                                items = emptyList(),
                                returned = 3
                            ),
                            events = Events(
                                available = 2,
                                collectionURI = "http://gateway.marvel.com/v1/public/characters/1/events",
                                items = emptyList(),
                                returned = 2
                            ),
                            urls = listOf(Url(type = "detail", url = "http://marvel.com/spiderman"))
                        )
                    ),
                    prevKey = null,
                    nextKey = 2
                )
            ),
            anchorPosition = 0,
            config = PagingConfig(pageSize = Constants.LIMIT),
            leadingPlaceholderCount = 0
        )

        // Criando uma instância do CharacterPagingSource
        pagingSource = CharacterPagingSource(httpRepository, name = null)

        // Verificando se a chave de refresh é a esperada
        val refreshKey = pagingSource.getRefreshKey(state)
        assertEquals(1, refreshKey)
    }
}
