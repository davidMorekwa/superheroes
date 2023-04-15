package com.example.myapplication.ui.logic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HeroUiState(
    @StringRes val heroName: Int = 0,
    @StringRes val heroDescription: Int = 0,
    @DrawableRes val heroImage: Int = 0
)
