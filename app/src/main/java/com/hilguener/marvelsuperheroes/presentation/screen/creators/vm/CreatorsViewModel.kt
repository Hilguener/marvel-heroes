package com.hilguener.marvelsuperheroes.presentation.screen.creators.vm

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
import com.hilguener.marvelsuperheroes.domain.usecase.state.CreatorsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreatorsViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    var state by mutableStateOf(CreatorsState())

    private val searchQuery = MutableStateFlow<String?>(null)

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val creatorsPager =
        searchQuery.flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
                pagingSourceFactory = { managerUseCase.getCreatorsPagingSource(query) },
            ).flow
                .cachedIn(viewModelScope)
        }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun getCreatorsComicsById(creatorId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoadingComics = true, error = null)
            val result =
                managerUseCase.getCreatorComicsById(creatorId) { errorMsg ->
                    eventChannel.send(Event.ShowError(errorMsg))
                }
            result?.let {
                state = state.copy(comics = it, isLoadingComics = false)
            } ?: run {
                state = state.copy(isLoadingComics = false, error = "Failed to load comics")
            }
        }
    }

    sealed class Event {
        data class ShowError(val message: String) : Event()

        data class ShowSuccess(val message: String = "Success") : Event()
    }
}
