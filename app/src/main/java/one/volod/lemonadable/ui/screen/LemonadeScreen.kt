package one.volod.lemonadable.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import one.volod.lemonadable.ui.LemonViewModel

@Composable
fun LemonadeScreen(
    modifier: Modifier = Modifier,
    lemonViewModel: LemonViewModel = viewModel()
) {
    val lemonUiState = lemonViewModel.lemonUiState.collectAsState()

    Scaffold() {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            LemonadeTextAndImage(
                text = lemonUiState.value.text,
                image = lemonUiState.value.image,
                onClick = { lemonViewModel.onClickHandler() }
            )
        }
    }
}

@Composable
fun LemonadeTextAndImage(
    text: String,
    @DrawableRes image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(image),
            contentDescription = text,
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}