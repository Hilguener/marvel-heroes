package com.hilguener.marvelsuperheroes.presentation.screen.stories.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class StoriesViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    val storiesPager =
        Pager(
            config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
            pagingSourceFactory = { managerUseCase.getStoriesPagingSource() },
        ).flow.cachedIn(
            viewModelScope,
        ).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    sealed class Event {
        data class ShowError(val message: String) : Event()

        data class ShowSuccess(val message: String = "Success") : Event()
    }
}
