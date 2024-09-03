package com.hilguener.marvelsuperheroes.presentation.screen.series.vm

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
import com.hilguener.marvelsuperheroes.domain.usecase.state.SeriesState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class SeriesViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    var state by mutableStateOf(SeriesState())

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    private val searchQuery = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val seriesPager =
        searchQuery.flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
                pagingSourceFactory = { managerUseCase.getSeriesPagingSource(query) },
            ).flow
                .cachedIn(viewModelScope)
        }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }

    sealed class Event {
        data class ShowError(val message: String) : Event()

        data class ShowSuccess(val message: String = "Success") : Event()
    }
}
