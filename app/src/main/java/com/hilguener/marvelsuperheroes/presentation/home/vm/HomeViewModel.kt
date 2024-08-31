package com.hilguener.marvelsuperheroes.presentation.home.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.domain.model.series.Series
import com.hilguener.marvelsuperheroes.domain.use_case.ManagerUseCase
import com.hilguener.marvelsuperheroes.domain.use_case.state.CharactersState
import com.hilguener.marvelsuperheroes.domain.use_case.state.ComicsState
import com.hilguener.marvelsuperheroes.presentation.screen.characters.vm.CharactersViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.comics.vm.ComicsViewModel.Event
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {

    var comicState by mutableStateOf(ComicsState())
    var characterState by mutableStateOf(CharactersState())

    private val _charactersCarrousel = MutableStateFlow<List<Character>>(emptyList())
    val charactersCarrousel: StateFlow<List<Character>> = _charactersCarrousel.asStateFlow()

    private val _comicsCarrousel = MutableStateFlow<List<Comic>>(emptyList())
    val comicsCarrousel: StateFlow<List<Comic>> = _comicsCarrousel.asStateFlow()

    private val _seriesCarrousel = MutableStateFlow<List<Series>>(emptyList())
    val seriesCarrousel: StateFlow<List<Series>> = _seriesCarrousel.asStateFlow()

    private val _eventsCarrousel =
        MutableStateFlow<List<com.hilguener.marvelsuperheroes.domain.model.events.Event>>(emptyList())
    val eventsCarrousel: StateFlow<List<com.hilguener.marvelsuperheroes.domain.model.events.Event>> =
        _eventsCarrousel.asStateFlow()

    private val _isLoadingCharacters = MutableStateFlow(false)
    val isLoadingCharacters: StateFlow<Boolean> = _isLoadingCharacters.asStateFlow()

    private val _isLoadingComics = MutableStateFlow(false)
    val isLoadingComics: StateFlow<Boolean> = _isLoadingComics.asStateFlow()

    private val _isLoadingSeries = MutableStateFlow(false)
    val isLoadingSeries: StateFlow<Boolean> = _isLoadingSeries.asStateFlow()

    private val _isLoadingEvents = MutableStateFlow(false)
    val isLoadingEvents: StateFlow<Boolean> = _isLoadingEvents.asStateFlow()

    private val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()

    sealed class Event {
        data class ShowError(val message: String) : Event()
        data class ShowSuccess(val data: List<Any>) : Event()
    }

    init {
        loadCarrousels()
    }

    private fun loadCarrousels() {
        loadCharactersCarrousel()
        loadComicsCarrousel()
        loadSeriesCarrousel()
        loadEventsCarrousel()
    }

    private fun loadCharactersCarrousel() {
        viewModelScope.launch {
            _isLoadingCharacters.value = true
            val characters = managerUseCase.getCharactersCarrousel { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            _isLoadingCharacters.value = false
            characters?.let {
                _charactersCarrousel.value = it
                _eventChannel.send(Event.ShowSuccess(it))
            }
        }
    }

    private fun loadComicsCarrousel() {
        viewModelScope.launch {
            _isLoadingComics.value = true
            val comics = managerUseCase.getComicsCarrousel { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            _isLoadingComics.value = false
            comics?.let {
                _comicsCarrousel.value = it
                _eventChannel.send(Event.ShowSuccess(it))
            }
        }
    }

    private fun loadSeriesCarrousel() {
        viewModelScope.launch {
            _isLoadingSeries.value = true
            val series = managerUseCase.getSeriesCarrousel { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            _isLoadingSeries.value = false
            series?.let {
                _seriesCarrousel.value = it
                _eventChannel.send(Event.ShowSuccess(it))
            }
        }
    }

    private fun loadEventsCarrousel() {
        viewModelScope.launch {
            _isLoadingEvents.value = true
            val series = managerUseCase.getEventsCarrousel { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            _isLoadingEvents.value = false
            series?.let {
                _eventsCarrousel.value = it
                _eventChannel.send(Event.ShowSuccess(it))
            }
        }
    }

    fun getCharactersComicById(comicId: Int) {
        viewModelScope.launch {
            comicState = comicState.copy(isLoading = true, error = null)
            val result = managerUseCase.getCharactersComicById(comicId) { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            result?.let {
                comicState = comicState.copy(characters = it, isLoading = false)
            } ?: run {
                comicState = comicState.copy(isLoading = false, error = "Failed to load comics")
            }
        }
    }

    fun getCharactersComicsById(characterId: Int) {
        viewModelScope.launch {
            characterState = characterState.copy(isLoadingComics = true, error = null)
            val result = managerUseCase.getCharactersComicsById(characterId) { errorMsg ->
                _eventChannel.send(Event.ShowError(errorMsg))
            }
            result?.let {
                characterState = characterState.copy(comics = it, isLoadingComics = false)
            } ?: run {
                characterState = characterState.copy(isLoadingComics = false, error = "Failed to load comics")
            }
        }
    }
}






