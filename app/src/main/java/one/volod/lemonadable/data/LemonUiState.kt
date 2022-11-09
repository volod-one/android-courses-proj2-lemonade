package one.volod.lemonadable.data

import androidx.annotation.DrawableRes

data class LemonUiState(
    var currentLemon: Int = 0,
    var text: String = "",
    @DrawableRes var image: Int = 0,
    var neededClicks: Int = 0,
    var clickCount: Int = 0,
)
