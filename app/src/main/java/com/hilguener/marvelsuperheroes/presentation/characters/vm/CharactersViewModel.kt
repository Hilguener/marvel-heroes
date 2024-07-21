package com.hilguener.marvelsuperheroes.presentation.characters.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.use_case.ManagerUseCase
import com.hilguener.marvelsuperheroes.domain.use_case.state.CharactersState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {

    var state by mutableStateOf(CharactersState())

    private val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()

    private val _favoriteCharacters = mutableStateOf<Set<Int>>(setOf())

    fun getCharacters() {
        viewModelScope.launch {
            state = state.copy(isLoadingCharacters = true, error = null)
            val result = managerUseCase.getCharactersUseCase { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            result?.let {
                state = state.copy(characters = it, isLoadingCharacters = false)
            } ?: run {
                state = state.copy(isLoadingCharacters = false, error = "Failed to load characters")
            }
        }
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

