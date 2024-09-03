package com.hilguener.marvelsuperheroes.presentation.screen.stories

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.hilguener.marvelsuperheroes.R
import com.hilguener.marvelsuperheroes.domain.model.stories.Story
import com.hilguener.marvelsuperheroes.presentation.screen.stories.vm.StoriesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StoriesScreen(modifier: Modifier = Modifier) {
    val viewModel: StoriesViewModel = koinViewModel()
    val stories = viewModel.storiesPager.collectAsLazyPagingItems()
    val context = LocalContext.current
    val bottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedStory = remember { mutableStateOf<Story?>(null) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is StoriesViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is StoriesViewModel.Event.ShowSuccess -> {
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
            selectedStory.value?.let { story ->
                StoryDetailContent(
                    story = story,
                )
            }
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        Column {
            Box(modifier = modifier.weight(1f)) {
                if (stories.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier =
                            modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(contentPadding = paddingValues, content = {
                        items(stories.itemCount) { index ->
                            val story = stories[index]
                            story?.let {
                                StoryItem(
                                    story = it,
                                    modifier =
                                        modifier.clickable {
                                            isSheetOpen = true
                                            selectedStory.value = it
                                            coroutineScope.launch {
                                                bottomSheetState.show()
                                            }
                                        },
                                )
                            }
                        }
                        if (stories.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier =
                                        modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                    contentAlignment = Alignment.Center,
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

@Composable
fun StoryItem(
    story: Story,
    modifier: Modifier,
) {
    ElevatedCard(modifier = modifier.padding(8.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel),
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = story.originalIssue.name,
                modifier = modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
            )
        }
    }
}

@Composable
fun StoryDetailContent(
    story: Story?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "Title",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = story?.title ?: "No title found",
            textAlign = TextAlign.Justify,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Creators",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        val creatorsText =
            if (story?.creators?.items.isNullOrEmpty()) {
                "No creators found"
            } else {
                story?.creators?.items?.joinToString { it.name } ?: "No creators found"
            }
        Text(
            text = creatorsText,
            textAlign = TextAlign.Justify,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Type",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(text = story?.type ?: "No type found")
    }
}
