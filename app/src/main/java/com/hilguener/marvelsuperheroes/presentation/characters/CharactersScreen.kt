package com.hilguener.marvelsuperheroes.presentation.characters

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.presentation.characters.vm.CharactersViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CharactersScreen(modifier: Modifier = Modifier) {
    val viewModel: CharactersViewModel = koinViewModel()
    val state = viewModel.state
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedCharacter = remember { mutableStateOf<Character?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val characters = viewModel.charactersPager.collectAsLazyPagingItems()
    val localFocusManager = LocalFocusManager.current
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
                    comics = state.comics,
                    isLoadingComics = state.isLoadingComics
                )
            }
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { query ->
                    searchQuery.value = query
                },
                label = { Text("Search characters...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.setSearchQuery(searchQuery.value.takeIf { it.isNotBlank() })
                        localFocusManager.clearFocus()
                    }
                )
            )
            Box(modifier = Modifier.weight(1f)) {
                if (characters.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (characters.itemCount == 0 && searchQuery.value.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No characters found",
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        contentPadding = paddingValues,
                        modifier = modifier.padding(horizontal = 16.dp),
                        content = {
                            items(characters.itemCount) { index ->
                                val character = characters[index]
                                character?.let {
                                    CharacterItem(character = it, modifier = Modifier.clickable {
                                        isSheetOpen = true
                                        selectedCharacter.value = it
                                        coroutineScope.launch {
                                            viewModel.getCharactersComicsById(it.id)
                                            bottomSheetState.show()
                                        }
                                    })
                                }
                            }

                            if (characters.loadState.append is LoadState.Loading) {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        })

                    if (characters.loadState.append is LoadState.Error) {
                        val e = characters.loadState.append as LoadState.Error
                        Toast.makeText(context, e.error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(bottom = 16.dp)
            .wrapContentSize()
    ) {
        GlideImage(
            model = "${character.thumbnail.path}.${character.thumbnail.extension}",
            contentDescription = character.name,
            modifier = modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = character.name,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailContent(
    character: Character,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    comics: List<Comic>,
    isLoadingComics: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            GlideImage(
                model = "${character.thumbnail.path}.${character.thumbnail.extension}",
                contentDescription = character.name,
                modifier = Modifier
                    .size(240.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-8).dp, y = (-8).dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = character.name,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Description",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 20.sp,
        )
        Text(
            text = character.description.takeIf { it.isNotBlank() } ?: "No description available.",
            textAlign = TextAlign.Justify,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Comics",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 20.sp,
        )
        if (isLoadingComics) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(comics) { comic ->
                    ComicItem(comic)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComicItem(comic: Comic, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(120.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
    ) {
        GlideImage(
            model = "${comic.thumbnail.path}.${comic.thumbnail.extension}",
            contentDescription = comic.title,
            modifier = modifier
                .height(180.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = comic.title,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
