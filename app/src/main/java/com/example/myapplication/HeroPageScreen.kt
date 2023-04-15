package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.logic.HeroUiState
import com.example.myapplication.ui.theme.MyApplicationTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroPageScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onBackClicked: ()-> Unit,
    uiState: HeroUiState,
) {
    Scaffold(
        topBar = { HeroTopBar(uiState = uiState) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = uiState.heroImage),
                contentDescription = "Hero Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 5.dp)
            )
//            Text(
//                text = stringResource(id = uiState.heroName),
//                style = MaterialTheme.typography.h3,
//                modifier = Modifier
//                    .padding(top = 10.dp)
//            )
            Text(
                text = stringResource(id = uiState.heroDescription),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(onClick = onBackClicked) {
                Text(text = "Back")
            }
        }
    }

}

@Composable
fun HeroTopBar(
    uiState: HeroUiState
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(text = stringResource(id = uiState.heroName), style = MaterialTheme.typography.h1)
    }
}

@Preview
@Composable
fun HeroPagePreview() {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            HeroPageScreen()
        }
    }
}