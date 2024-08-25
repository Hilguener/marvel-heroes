package com.hilguener.marvelsuperheroes.presentation.screen.creators

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.domain.model.creators.Creator
import com.hilguener.marvelsuperheroes.presentation.screen.characters.ComicItem
import com.hilguener.marvelsuperheroes.presentation.screen.creators.vm.CreatorsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreatorsScreen(modifier: Modifier = Modifier) {
    val viewModel: CreatorsViewModel = koinViewModel()
    val state = viewModel.state
    val creators = viewModel.creatorsPager.collectAsLazyPagingItems()
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val searchQuery = rememberSaveable { mutableStateOf("") }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedCreator = remember { mutableStateOf<Creator?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val localFocusManager = LocalFocusManager.current
    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is CreatorsViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is CreatorsViewModel.Event.ShowSuccess -> {
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
            selectedCreator.value?.let { creator ->
                CreatorDetailContent(
                    creator = creator,
                    comics = state.comics,
                    isLoadingComics = state.isLoadingComics
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
                label = { Text("Search creators...") },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.setSearchQuery(searchQuery.value.takeIf { it.isNotBlank() })
                    localFocusManager.clearFocus()
                })
            )
            Box(modifier = modifier.weight(1f)) {
                if (creators.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(contentPadding = paddingValues, content = {
                        items(creators.itemCount) { index ->
                            val creator = creators[index]
                            creator?.let {
                                CreatorItem(creator = it, modifier = modifier.clickable {
                                    isSheetOpen = true
                                    selectedCreator.value = it
                                    coroutineScope.launch {
                                        viewModel.getCreatorsComicsById(it.id)
                                        bottomSheetState.show()
                                    }
                                })
                            }
                        }
                        if (creators.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CreatorDetailContent(
    creator: Creator?, modifier: Modifier = Modifier, comics: List<Comic>,
    isLoadingComics: Boolean,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = "${creator?.thumbnail?.path}/portrait_uncanny.${creator?.thumbnail?.extension}",
                contentDescription = creator?.fullName,
                modifier = modifier
                    .width(200.dp)
                    .height(260.dp)
                    .clip(RoundedCornerShape(40.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = modifier.width(16.dp))

            Text(
                text = creator?.fullName ?: "No full name found",
                style = MaterialTheme.typography.displaySmall,
                fontSize = 24.sp,
                modifier = modifier.fillMaxWidth()
            )

        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Written Comics",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 20.sp,
        )

        Spacer(modifier = modifier.height(8.dp))

        if (isLoadingComics) {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(comics) { comic ->
                    ComicItem(comic)
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CreatorItem(creator: Creator?, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier.padding(8.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GlideImage(
                model = creator?.thumbnail?.path + "/portrait_uncanny." + creator?.thumbnail?.extension,
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = creator?.fullName ?: "No full name found",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
            )
        }
    }
}
