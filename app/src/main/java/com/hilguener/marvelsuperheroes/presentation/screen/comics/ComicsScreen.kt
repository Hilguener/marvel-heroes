package com.hilguener.marvelsuperheroes.presentation.screen.comics

import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.graphicsLayer
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
import com.hilguener.marvelsuperheroes.presentation.screen.characters.CharacterItem
import com.hilguener.marvelsuperheroes.presentation.screen.comics.vm.ComicsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ComicsScreen(modifier: Modifier = Modifier) {
    val viewModel: ComicsViewModel = koinViewModel()
    val state = viewModel.state
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedComic = remember { mutableStateOf<Comic?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val comics = viewModel.comicsPager.collectAsLazyPagingItems()
    var clickedItemId by rememberSaveable { mutableStateOf<Int?>(null) }

    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is ComicsViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is ComicsViewModel.Event.ShowSuccess -> {
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
            selectedComic.value?.let { comic ->
                ComicDetailContent(
                    comic = comic,
                    characters = state.characters,
                    isLoadingCharacters = state.isLoading
                )
            }
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        Column {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { query ->
                    searchQuery.value = query
                },
                label = { Text("Search comics...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.setSearchQuery(searchQuery.value.takeIf { it.isNotBlank() })
                        localFocusManager.clearFocus()
                    }
                )
            )
            Box(modifier = Modifier.fillMaxSize()) {
                if (comics.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (comics.itemCount == 0 && searchQuery.value.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No comics found",
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = paddingValues,
                        modifier = modifier.padding(horizontal = 16.dp),
                        content = {
                            items(comics.itemCount) { index ->
                                val comic = comics[index]
                                comic?.let {
                                    ComicItem(
                                        comic = it,
                                        modifier = Modifier
                                            .clickable {
                                                isSheetOpen = true
                                                selectedComic.value = it
                                                clickedItemId = it.id
                                                coroutineScope.launch {
                                                    viewModel.getCharactersComicById(it.id)
                                                    bottomSheetState.show()
                                                }
                                            }
                                    )
                                }
                            }

                            if (comics.loadState.append is LoadState.Loading) {
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
                        }
                    )

                    if (comics.loadState.append is LoadState.Error) {
                        val e = comics.loadState.append as LoadState.Error
                        Toast.makeText(context, e.error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComicDetailContent(
    comic: Comic?,
    isLoadingCharacters: Boolean,
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                GlideImage(
                    model = "${comic?.thumbnail?.path}/portrait_fantastic.${comic?.thumbnail?.extension}",
                    contentDescription = comic?.title,
                    modifier = Modifier
                        .width(200.dp)
                        .height(260.dp)
                        .clip(RoundedCornerShape(40.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = comic?.title ?: "No title found",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = "Description",
                style = MaterialTheme.typography.displaySmall,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = comic?.description.takeIf { it!!.isNotBlank() }
                    ?: "No description available.",
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 17.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row {
                Text(
                    text = "Pages: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = "${comic?.pageCount}",
                    fontSize = 17.sp,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row {
                Text(
                    text = "ISBN: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = comic?.isbn.takeIf { it!!.isNotBlank() } ?: "Unknown",
                    fontSize = 17.sp,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row {
                Text(
                    text = "Format: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = comic?.format.takeIf { it!!.isNotBlank() } ?: "Unknown",
                    fontSize = 17.sp,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = "Characters",
                style = MaterialTheme.typography.displaySmall,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        when {
            isLoadingCharacters -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            characters.isEmpty() -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No characters found",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 17.sp
                        )
                    }
                }
            }

            else -> {
                items(characters) { character ->
                    CharacterItem(character)
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComicItem(comic: Comic, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        GlideImage(
            model = "${comic.thumbnail.path}/portrait_incredible.${comic.thumbnail.extension}",
            contentDescription = comic.title,
            modifier = modifier
                .size(270.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp)),
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = comic.title,
            style = MaterialTheme.typography.displaySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

