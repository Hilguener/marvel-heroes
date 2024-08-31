package com.hilguener.marvelsuperheroes.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.domain.model.events.Event
import com.hilguener.marvelsuperheroes.domain.model.series.Series
import com.hilguener.marvelsuperheroes.presentation.home.vm.HomeViewModel
import com.hilguener.marvelsuperheroes.presentation.navigation.AppBar
import com.hilguener.marvelsuperheroes.presentation.navigation.DrawerBody
import com.hilguener.marvelsuperheroes.presentation.navigation.DrawerHeader
import com.hilguener.marvelsuperheroes.presentation.navigation.NavDrawerItem
import com.hilguener.marvelsuperheroes.presentation.screen.characters.CharacterDetailContent
import com.hilguener.marvelsuperheroes.presentation.screen.characters.CharactersScreen
import com.hilguener.marvelsuperheroes.presentation.screen.comics.ComicDetailContent
import com.hilguener.marvelsuperheroes.presentation.screen.comics.ComicsScreen
import com.hilguener.marvelsuperheroes.presentation.screen.creators.CreatorsScreen
import com.hilguener.marvelsuperheroes.presentation.screen.events.EventDetailContent
import com.hilguener.marvelsuperheroes.presentation.screen.events.EventsScreen
import com.hilguener.marvelsuperheroes.presentation.screen.series.SeriesDetailContent
import com.hilguener.marvelsuperheroes.presentation.screen.series.SeriesScreen
import com.hilguener.marvelsuperheroes.presentation.screen.stories.StoriesScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val appBarTitle = when (currentRoute) {
        "my_app" -> "Home"
        "characters_screen" -> "Characters"
        "comics_screen" -> "Comics"
        "events_screen" -> "Events"
        "stories_screen" -> "Stories"
        "creators_screen" -> "Creators"
        "series_screen" -> "Series"
        else -> ""
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            DrawerHeader()
            HorizontalDivider()
            DrawerBody(items = listOf(
                NavDrawerItem(
                    id = "home", title = "Home", icon = Icons.Default.Home
                ), NavDrawerItem(
                    id = "characters", title = "Characters", icon = Icons.Default.People
                ), NavDrawerItem(
                    id = "comics", title = "Comics", icon = Icons.Default.Book
                ), NavDrawerItem(
                    id = "events", title = "Events", icon = Icons.Default.CalendarToday
                ), NavDrawerItem(
                    id = "stories", title = "Stories", icon = Icons.Default.AutoStories
                ), NavDrawerItem(
                    id = "creators", title = "Creators", icon = Icons.Default.Edit
                ), NavDrawerItem(
                    id = "series", title = "Series", icon = Icons.Default.Tv
                )
            ), onItemClick = { item ->
                when (item.id) {
                    "home" -> {
                        navController.navigate("my_app") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }

                    "characters" -> {
                        navController.navigate("characters_screen") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }

                    "comics" -> {
                        navController.navigate("comics_screen") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }

                    "series" -> {
                        navController.navigate("series_screen") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }

                    "events" -> {
                        navController.navigate("events_screen") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }

                    "creators" -> {
                        navController.navigate("creators_screen") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }

                    "stories" -> {
                        navController.navigate("stories_screen") {
                            popUpTo("my_app") { inclusive = true }
                        }
                    }
                }
                scope.launch {
                    drawerState.close()
                }
            })
            HorizontalDivider()
        }
    }) {
        Scaffold(topBar = {
            AppBar(title = appBarTitle, onNavigationIconClick = {
                scope.launch {
                    drawerState.open()
                }
            })
        }) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = "my_app",
                modifier.padding(contentPadding)
            ) {
                composable("my_app") {
                    MainScreenContent()
                }
                composable("characters_screen") {
                    CharactersScreen()
                }
                composable("comics_screen") {
                    ComicsScreen()
                }
                composable("series_screen") {
                    SeriesScreen()
                }
                composable("events_screen") {
                    EventsScreen()
                }
                composable("stories_screen") {
                    StoriesScreen()
                }
                composable("creators_screen") {
                    CreatorsScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = koinViewModel()
    val comicState = viewModel.comicState
    val charactersState = viewModel.characterState
    val characters by viewModel.charactersCarrousel.collectAsState()
    val comics by viewModel.comicsCarrousel.collectAsState()
    val series by viewModel.seriesCarrousel.collectAsState()
    val events by viewModel.eventsCarrousel.collectAsState()
    val isLoadingCharacters by viewModel.isLoadingCharacters.collectAsState()
    val isLoadingComics by viewModel.isLoadingComics.collectAsState()
    val isLoadingSeries by viewModel.isLoadingSeries.collectAsState()
    val isLoadingEvents by viewModel.isLoadingEvents.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isCharacterSheetOpen by rememberSaveable { mutableStateOf(false) }
    var isComicSheetOpen by rememberSaveable { mutableStateOf(false) }
    var isEventSheetOpen by rememberSaveable { mutableStateOf(false) }
    var isSeriesSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedCharacter = remember { mutableStateOf<Character?>(null) }
    val selectedComic = remember { mutableStateOf<Comic?>(null) }
    val selectedEvent = remember { mutableStateOf<Event?>(null) }
    val selectedSeries = remember { mutableStateOf<Series?>(null) }
    var clickedItemId by rememberSaveable { mutableStateOf<Int?>(null) }
    val coroutineScope = rememberCoroutineScope()

    if (isCharacterSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { isCharacterSheetOpen = false },
        ) {
            selectedCharacter.value?.let { character ->
                CharacterDetailContent(
                    character = character,
                    comics = charactersState.comics,
                    isLoadingComics = charactersState.isLoadingComics
                )
            }
        }
    }

    if (isComicSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { isComicSheetOpen = false },
        ) {
            selectedComic.value?.let { comic ->
                ComicDetailContent(
                    comic = comic,
                    characters = comicState.characters,
                    isLoadingCharacters = comicState.isLoading
                )
            }
        }
    }
    if (isEventSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { isEventSheetOpen = false },
        ) {
            selectedEvent.value?.let { event ->
                EventDetailContent(event = event)
            }
        }
    }

    if (isSeriesSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { isSeriesSheetOpen = false },
        ) {
            selectedSeries.value?.let { serie ->
                SeriesDetailContent(series = serie)
            }
        }
    }

    Column(
        modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Characters", style = MaterialTheme.typography.displaySmall, fontSize = 20.sp)

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp), contentAlignment = Alignment.Center
        ) {
            if (isLoadingCharacters) {
                CircularProgressIndicator()
            } else {
                LazyRow {
                    items(characters) { character ->
                        MainCharacterItem(character = character, modifier = modifier.clickable {
                            clickedItemId = character.id
                            isCharacterSheetOpen = true
                            selectedCharacter.value = character
                            coroutineScope.launch {
                                viewModel.getCharactersComicsById(character.id)
                                bottomSheetState.show()
                            }

                        })
                    }
                }
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(text = "Comics", style = MaterialTheme.typography.displaySmall, fontSize = 20.sp)

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp), contentAlignment = Alignment.Center
        ) {
            if (isLoadingComics) {
                CircularProgressIndicator()
            } else {
                LazyRow {
                    items(comics) { comic ->
                        MainComicItem(comic = comic, modifier = modifier.clickable {
                            clickedItemId = comic.id
                            isComicSheetOpen = true
                            selectedComic.value = comic
                            coroutineScope.launch {
                                viewModel.getCharactersComicById(comic.id)
                                bottomSheetState.show()
                            }
                        })
                    }
                }
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(text = "Series", style = MaterialTheme.typography.displaySmall, fontSize = 20.sp)

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp), contentAlignment = Alignment.Center
        ) {
            if (isLoadingSeries) {
                CircularProgressIndicator()
            } else {
                LazyRow {
                    items(series) { serie ->
                        MainSeriesItem(serie = serie, modifier = modifier.clickable {
                            clickedItemId = serie.id
                            isSeriesSheetOpen = true
                            selectedSeries.value = serie
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        })
                    }
                }
            }
        }
        Spacer(modifier = modifier.height(16.dp))

        Text(text = "Events", style = MaterialTheme.typography.displaySmall, fontSize = 20.sp)

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp), contentAlignment = Alignment.Center
        ) {
            if (isLoadingEvents) {
                CircularProgressIndicator()
            } else {
                LazyRow {
                    items(events) { event ->
                        MainEventItem(event = event, modifier = modifier.clickable {
                            clickedItemId = event.id
                            isEventSheetOpen = true
                            selectedEvent.value = event
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainEventItem(event: Event, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.padding(8.dp)
    ) {
        GlideImage(
            model = "${event.thumbnail.path}/portrait_uncanny.${event.thumbnail.extension}",
            contentDescription = event.title,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.None
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainSeriesItem(serie: Series, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.padding(8.dp)
    ) {
        GlideImage(
            model = "${serie.thumbnail.path}/portrait_uncanny.${serie.thumbnail.extension}",
            contentDescription = serie.title,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.None
        )
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainComicItem(comic: Comic, modifier: Modifier = Modifier) {

    ElevatedCard(
        modifier = modifier.padding(8.dp)
    ) {
        GlideImage(
            model = "${comic.thumbnail.path}/portrait_uncanny.${comic.thumbnail.extension}",
            contentDescription = comic.title,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.None
        )
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainCharacterItem(
    character: Character, modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.padding(8.dp)
    ) {
        GlideImage(
            model = "${character.thumbnail.path}/portrait_uncanny.${character.thumbnail.extension}",
            contentDescription = character.name,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.None
        )
    }
}







