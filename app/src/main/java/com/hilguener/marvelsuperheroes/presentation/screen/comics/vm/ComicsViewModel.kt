package com.hilguener.marvelsuperheroes.presentation.screen.comics.vm

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
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.state.ComicsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ComicsViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    var state by mutableStateOf(ComicsState())

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    private val searchQuery = MutableStateFlow<String?>(null)

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val comicsPager =
        searchQuery.flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
                pagingSourceFactory = { managerUseCase.getComicsPagingSource(query) },
            ).flow.cachedIn(viewModelScope)
        }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun getCharactersComicById(comicId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            val result =
                managerUseCase.getCharactersComicById(comicId) { errorMsg ->
                    eventChannel.send(Event.ShowError(errorMsg))
                }
            result?.let {
                state = state.copy(characters = it, isLoading = false)
            } ?: run {
                state = state.copy(isLoading = false, error = "Failed to load comics")
            }
        }
    }

    sealed class Event {
        data class ShowError(val message: String) : Event()

        data class ShowSuccess(val message: String = "Success") : Event()
    }
}
