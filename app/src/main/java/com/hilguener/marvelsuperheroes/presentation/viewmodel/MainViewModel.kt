package com.hilguener.marvelsuperheroes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val managerUseCase: ManagerUseCase) : ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> get() = _characters

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> get() = _error

    fun getCharacters() {
        viewModelScope.launch {
            val result =
                managerUseCase.getCharactersUseCase { errorMsg ->
                    _error.emit(errorMsg)
                }
            result?.let {
                _characters.value = listOf(it)
            }
        }
    }
}
