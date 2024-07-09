package com.hilguener.marvelsuperheroes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MarvelSuperHeroesTheme
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MarvelSuperHeroesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LaunchedEffect(Unit) {
                        viewModel.getCharacters()
                    }
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    val characterList by viewModel.characters.collectAsState()

    Column(modifier = modifier.padding(8.dp)) {

    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Text(
        text = character.name,
        modifier = modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelSuperHeroesTheme {
//        Greeting("Android")
    }
}