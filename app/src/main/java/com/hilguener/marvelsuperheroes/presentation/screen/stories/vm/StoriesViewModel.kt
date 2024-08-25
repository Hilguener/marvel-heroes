package com.hilguener.marvelsuperheroes.presentation.screen.stories.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.use_case.ManagerUseCase
import com.hilguener.marvelsuperheroes.presentation.screen.characters.vm.CharactersViewModel.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class StoriesViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    private val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()


    val storiesPager =
        Pager(config = PagingConfig(pageSize = Constants.LIMIT, enablePlaceholders = false),
            pagingSourceFactory = { managerUseCase.getStoriesPagingSource() }).flow.cachedIn(
            viewModelScope
        ).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    sealed class Event {
        data class ShowError(val message: String) : Event()
        data class ShowSuccess(val message: String = "Success") : Event()
    }
}