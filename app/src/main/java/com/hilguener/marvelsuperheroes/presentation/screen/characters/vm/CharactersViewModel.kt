package com.hilguener.marvelsuperheroes.presentation.screen.characters.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.use_case.ManagerUseCase
import com.hilguener.marvelsuperheroes.domain.use_case.state.CharactersState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class CharactersViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {

    var state by mutableStateOf(CharactersState())

    private val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()

    private val _favoriteCharacters = mutableStateOf<Set<Int>>(setOf())

    private val searchQuery = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val charactersPager = searchQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
            pagingSourceFactory = { managerUseCase.getCharactersPagingSource(query) }
        ).flow
            .cachedIn(viewModelScope)
    }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }

    fun getCharactersComicsById(characterId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoadingComics = true, error = null)
            val result = managerUseCase.getCharactersComicsById(characterId) { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            result?.let {
                state = state.copy(comics = it, isLoadingComics = false)
            } ?: run {
                state = state.copy(isLoadingComics = false, error = "Failed to load comics")
            }
        }
    }

    fun addFavorite(character: Character) {
        viewModelScope.launch {
            _favoriteCharacters.value += character.id
            _eventChannel.send(Event.ShowSuccess())
        }
    }

    fun removeFavorite(character: Character) {
        viewModelScope.launch {
            _favoriteCharacters.value -= character.id
            _eventChannel.send(Event.ShowSuccess())
        }
    }

    fun isFavorite(character: Character): Boolean {
        return _favoriteCharacters.value.contains(character.id)
    }

    fun getFavoriteCharacters(allCharacters: List<Character>): List<Character> {
        return allCharacters.filter { character ->
            _favoriteCharacters.value.contains(character.id)
        }
    }

    sealed class Event {
        data class ShowError(val message: String) : Event()
        data class ShowSuccess(val message: String = "Success") : Event()
    }
}



