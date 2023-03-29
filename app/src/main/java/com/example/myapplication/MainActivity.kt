package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Hero
import com.example.myapplication.model.HeroesRepository
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HeroesList()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroesList() {

    Scaffold(
        topBar = { TopBar() }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ){
            items(HeroesRepository.heroes){
                    item: Hero -> HeroListItem(
                hero = item,
            )
            }
        }
    }

}


@Composable
fun HeroListItem(hero: Hero) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Surface(
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp)
            .clickable { expanded = !expanded }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp)
        ) {
            HeroInformation(
                name = hero.name,
                desc = hero.description,
                expand = expanded
//                modifier = Modifier.weight(2f)
            )
            HeroIcon(
                image = hero.image,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes name: Int,
    @StringRes desc: Int,
    modifier: Modifier = Modifier,
    expand: Boolean,
) {
    Column() {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.h3,
//            modifier = Modifier
//                .padding(top = 10.dp)
        )
        AnimatedVisibility(
            visible = expand,
            enter = slideInHorizontally(spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow)),
            exit = fadeOut()
        ) {
            Text(
                text = stringResource(id = desc),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .width(300.dp)
            )
        }


    }
}

@Composable
fun HeroIcon(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .size(70.dp)

    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Hero icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable { }
        )
    }
    
}

@Composable
fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(text = "Superheroes", style = MaterialTheme.typography.h1)
    }

}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(darkTheme = false) {
//        HeroesList()
    }
}