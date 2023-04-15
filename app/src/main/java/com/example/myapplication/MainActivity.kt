package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.model.Hero
import com.example.myapplication.model.HeroesRepository
import com.example.myapplication.model.Screens
import com.example.myapplication.ui.logic.HeroViewModel
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
                    HeroScreen()
                }
            }
        }
    }
}

@Composable
fun HeroScreen() {
    var heroViewModel = HeroViewModel()
    var heroUiState = heroViewModel.heroUiState.collectAsState()
    var navController = rememberNavController()



    NavHost(
        navController = navController,
        startDestination = Screens.ListPage.toString()
    ){
        composable(route = Screens.ListPage.toString()){
            HeroesList(
                viewModel = heroViewModel,
                nav = navController
            )
        }
        composable(route = Screens.HeroPage.toString()){
            HeroPageScreen(
                onBackClicked = {
                    navController.popBackStack(route = Screens.ListPage.toString(), inclusive = false)
                },
                uiState = heroUiState.value
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(darkTheme = false) {
//        HeroesList()
    }
}