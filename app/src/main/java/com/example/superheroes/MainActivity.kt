package com.example.superheroes

import android.content.res.Resources
import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SuperheroesApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold(
        topBar = {
            SuperheroesTopBar()
        }
    ) {
        it ->
        LazyColumn(contentPadding = it) {
            items(heroes) {
                SuperheroesCard(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun SuperheroesCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(start = 16.dp, end = 16.dp,bottom = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            SuperheroeInformation(hero.nameRes, hero.descriptionRes, modifier.weight(1f))
            HeroIcon(hero.imageRes)
        }
    }
}

@Composable
fun HeroIcon(image: Int, modifier: Modifier = Modifier) {
    Box(
    ) {
        Image(
            modifier = modifier.size(72.dp).clip(MaterialTheme.shapes.small),
            painter = painterResource(image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
fun SuperheroeInformation(heroName: Int, heroDes: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.height(72.dp).padding(end = 16.dp)
    ) {
        Text(text = stringResource(heroName), style = MaterialTheme.typography.displaySmall)
        Text(text = stringResource(heroDes), style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperheroesTheme {
        SuperheroesCard(
            Hero(
                nameRes = R.string.hero1,
                descriptionRes = R.string.description1,
                imageRes = R.drawable.android_superhero1
            )
        )
    }
}