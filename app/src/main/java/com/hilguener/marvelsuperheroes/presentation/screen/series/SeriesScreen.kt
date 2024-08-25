package com.hilguener.marvelsuperheroes.presentation.screen.series

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.hilguener.marvelsuperheroes.domain.model.series.Series
import com.hilguener.marvelsuperheroes.presentation.screen.series.vm.SeriesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SeriesScreen(modifier: Modifier = Modifier) {
    val viewModel: SeriesViewModel = koinViewModel()
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val series = viewModel.seriesPager.collectAsLazyPagingItems()
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedSeries = remember { mutableStateOf<Series?>(null) }

    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is SeriesViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is SeriesViewModel.Event.ShowSuccess -> {
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
            selectedSeries.value?.let { series ->
                SeriesDetailContent(
                    series = series
                )
            }
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        Column(modifier = modifier.fillMaxSize()) {
            OutlinedTextField(value = searchQuery.value,
                onValueChange = { query ->
                    searchQuery.value = query
                },
                label = { Text("Search series...") },
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
                }))
            Box(modifier = modifier.weight(1f)) {
                if (series.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (series.itemCount == 0 && searchQuery.value.isNotBlank()) {
                    Box(
                        modifier = modifier
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
                    LazyColumn(contentPadding = paddingValues,
                        modifier = modifier.padding(horizontal = 16.dp),
                        content = {
                            items(series.itemCount) { index ->
                                val serie = series[index]
                                serie?.let {
                                    SeriesItem(series = it, modifier = modifier.clickable {
                                        isSheetOpen = true
                                        selectedSeries.value = it
                                        coroutineScope.launch {
                                            bottomSheetState.show()
                                        }
                                    })
                                }
                            }

                            if (series.loadState.append is LoadState.Loading) {
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

                    if (series.loadState.append is LoadState.Error) {
                        val e = series.loadState.append as LoadState.Error
                        Toast.makeText(context, e.error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SeriesDetailContent(series: Series?, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                GlideImage(
                    model = "${series?.thumbnail?.path}/portrait_fantastic.${series?.thumbnail?.extension}",
                    contentDescription = series?.title,
                    modifier = modifier
                        .width(200.dp)
                        .height(260.dp)
                        .clip(RoundedCornerShape(40.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = modifier.width(16.dp))
                Column {
                    Text(
                        text = series?.title ?: "",
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center,
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Text(
                text = "Description",
                style = MaterialTheme.typography.displaySmall,
                fontSize = 17.sp,
            )
        }
        item {
            Text(
                text = series?.description?.takeIf { it.isNotBlank() }
                    ?: "No description available.",
                textAlign = TextAlign.Justify,
            )
        }
        item {
            Row {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Type: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = series?.type?.takeIf { it.isNotBlank() } ?: "No type available.",
                )
            }

        }

        item {
            Row {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Rating: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = series?.rating?.takeIf { it.isNotBlank() } ?: "No rating available.",
                )
            }

        }
        item {
            Row {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Start Year: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = series?.startYear?.toString()?.takeIf { it.isNotBlank() } ?: "No start year available.",
                )
            }
        }
        item {
            Row {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "End Year: ",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 17.sp,
                )
                Text(
                    text = series?.endYear?.toString()?.takeIf { it.isNotBlank() } ?: "No end year available.",
                )
            }
        }
    }
}


@Composable
fun SeriesItem(
    series: Series,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.padding(8.dp)
    ) {
        Column {
            SeriesIcon(image = "${series.thumbnail.path}.${series.thumbnail.extension}")
            Spacer(modifier = modifier.height(8.dp))
            SeriesInfo(
                seriesTitle = series.title,
            )
        }
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SeriesIcon(
    image: String,
    modifier: Modifier = Modifier,
) {
    GlideImage(
        model = image,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .sizeIn(72.dp),
        contentScale = ContentScale.None,
        contentDescription = null,
    )
}

@Composable
fun SeriesInfo(
    seriesTitle: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = seriesTitle,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )

    }
}

