package com.example.myapplication.ui.logic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HeroViewModel: ViewModel() {
    private var _heroUiState: MutableStateFlow<HeroUiState> = MutableStateFlow(HeroUiState())
    val heroUiState = _heroUiState.asStateFlow()

    fun updateUiState(
        @StringRes name: Int,
        @StringRes description: Int,
        @DrawableRes img: Int
    ){
        _heroUiState.update { state ->
            state.copy(
                heroName = name,
                heroDescription = description,
                heroImage = img
            )
        }
    }
}