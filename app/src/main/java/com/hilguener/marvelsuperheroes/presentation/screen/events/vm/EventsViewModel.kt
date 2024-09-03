package com.hilguener.marvelsuperheroes.presentation.screen.events.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class EventsViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    private val searchQuery = MutableStateFlow<String?>(null)

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val eventsPager =
        searchQuery.flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
                pagingSourceFactory = { managerUseCase.getEventsPagingSource(query) },
            ).flow
                .cachedIn(viewModelScope)
        }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    sealed class Event {
        data class ShowError(val message: String) : Event()

        data class ShowSuccess(val message: String = "Success") : Event()
    }
}
