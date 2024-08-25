package com.hilguener.marvelsuperheroes.presentation.screen.favorites

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.presentation.screen.characters.CharacterDetailContent
import com.hilguener.marvelsuperheroes.presentation.screen.characters.CharacterItem
import com.hilguener.marvelsuperheroes.presentation.screen.characters.vm.CharactersViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val viewModel: CharactersViewModel = koinViewModel()
    val allCharacters by remember { mutableStateOf(viewModel.state.characters) }
    val favoriteHeroes = viewModel.getFavoriteCharacters(allCharacters)
    val bottomSheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedCharacter = remember { mutableStateOf<Character?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is CharactersViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is CharactersViewModel.Event.ShowSuccess -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { isSheetOpen = false },
        ) {
            selectedCharacter.value?.let { character ->
                CharacterDetailContent(
                    character = character,
                    isFavorite = viewModel.isFavorite(character),
                    onFavoriteClick = {
                        if (viewModel.isFavorite(character)) {
                            viewModel.removeFavorite(character)
                        } else {
                            viewModel.addFavorite(character)
                        }
                    },
                    comics = viewModel.state.comics,
                    isLoadingComics = viewModel.state.isLoadingComics
                )
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (favoriteHeroes.isEmpty()) {
            Text(text = "No favorite characters yet.")
        } else {
            LazyColumn {
                items(favoriteHeroes) { character ->
                    CharacterItem(
                        character = character,
                        modifier = Modifier.clickable {
                            selectedCharacter.value = character
                            isSheetOpen = true
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        }
                    )
                }
            }
        }
    }
}


