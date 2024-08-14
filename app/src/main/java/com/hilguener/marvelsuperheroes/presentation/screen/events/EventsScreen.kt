package com.hilguener.marvelsuperheroes.presentation.screen.events

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hilguener.marvelsuperheroes.domain.model.events.Event
import com.hilguener.marvelsuperheroes.presentation.screen.events.vm.EventsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun EventsScreen(modifier: Modifier = Modifier) {
    val viewModel: EventsViewModel = koinViewModel()
    val context = LocalContext.current
    val events = viewModel.eventsPager.collectAsLazyPagingItems()
    val localFocusManager = LocalFocusManager.current
    val searchQuery = rememberSaveable { mutableStateOf("") }
    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is EventsViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is EventsViewModel.Event.ShowSuccess -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        Column(modifier = modifier.fillMaxSize()) {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { query ->
                    searchQuery.value = query
                },
                label = { Text("Search events...") },
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
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.setSearchQuery(searchQuery.value.takeIf { it.isNotBlank() })
                        localFocusManager.clearFocus()
                    }
                )
            )
            Box(modifier = modifier.weight(1f)) {
                if (events.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (events.itemCount == 0 && searchQuery.value.isNotBlank()) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No events found",
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = modifier.padding(horizontal = 16.dp),
                        content = {
                            items(events.itemCount) { index ->
                                val event = events[index]
                                event?.let {
                                    EventItem(event = it)
                                }
                            }
                            if (events.loadState.append is LoadState.Loading) {
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

                    if (events.loadState.append is LoadState.Error) {
                        val e = events.loadState.append as LoadState.Error
                        Toast.makeText(context, e.error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EventItem(event: Event?, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .wrapContentSize()
        ) {
            GlideImage(
                model = "${event?.thumbnail?.path}.${event?.thumbnail?.extension}",
                contentDescription = null,
                modifier = modifier
                    .width(200.dp)
                    .height(260.dp),
                contentScale = ContentScale.None
            )
            Spacer(modifier = modifier.width(8.dp))
            Column {
                Text(
                    text = event?.title?.takeIf { it.isNotBlank() } ?: "No title available.",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
            }

        }
    }

}

