package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.model.Screens
import com.example.myapplication.ui.logic.HeroViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroScreen() {
    var heroViewModel = HeroViewModel()
    var heroUiState = heroViewModel.heroUiState.collectAsState()
//    var navController = rememberNavController()
    var navController = rememberAnimatedNavController()


    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.ListPage.toString()
    ){
        composable(
            route = Screens.ListPage.toString(),
            enterTransition = { fadeIn() + slideInHorizontally() }
        ){
            HeroesList(
                viewModel = heroViewModel,
                nav = navController
            )
        }
        composable(
            route = Screens.HeroPage.toString(),
            enterTransition = {
               fadeIn() + slideInHorizontally(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow))
            },
            popExitTransition = { slideOutVertically(tween(300)) }
        ){
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