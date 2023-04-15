package com.example.myapplication

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.model.Hero
import com.example.myapplication.model.HeroesRepository
import com.example.myapplication.model.Screens
import com.example.myapplication.ui.logic.HeroViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroesList(
    viewModel: HeroViewModel,
    nav: NavController
) {
    @StringRes var heroName by rememberSaveable {
        mutableStateOf(0)
    }
    @StringRes var heroDesc by rememberSaveable {
        mutableStateOf(0)
    }
    @DrawableRes var heroImage by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = { TopBar() }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ){
            items(HeroesRepository.heroes){
                    item: Hero -> HeroListItem(
                    hero = item,
                    onSurfaceClicked = {
//                        viewModel.updateUiState(item)
                        heroName = item.name
                        heroDesc = item.description
                        heroImage = item.image
                        viewModel.updateUiState(
                            name = heroName,
                            description = heroDesc,
                            img = heroImage,
                        )
                        
                        nav.navigate(Screens.HeroPage.toString())
                    }
                )
            }
        }
    }

}


@Composable
fun HeroListItem(
    hero: Hero,
    onSurfaceClicked: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
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
            .clickable { }
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
                expand = expanded,
//                modifier = Modifier.weight(2f)
                onNextClicked = onSurfaceClicked
            )
            HeroIcon(
                image = hero.image,
                modifier = Modifier
                    .weight(1f),
                onCLicked = onSurfaceClicked
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
    onNextClicked: () -> Unit

) {
    Column() {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.h3,
//            modifier = Modifier
//                .padding(top = 10.dp)
        )
//        AnimatedVisibility(
//            visible = expand,
//            enter = slideInHorizontally(spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow)),
//            exit = fadeOut()
//        ) {
//            Text(
//                text = stringResource(id = desc),
//                style = MaterialTheme.typography.body1,
//                modifier = Modifier
//                    .width(300.dp)
//            )
//        }


    }
}

@Composable
fun HeroIcon(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    onCLicked: ()->Unit
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
                .clickable { onCLicked() }
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