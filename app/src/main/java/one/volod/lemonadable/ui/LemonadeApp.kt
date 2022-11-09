package one.volod.lemonadable.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import one.volod.lemonadable.ui.screen.LemonadeScreen
import one.volod.lemonadable.ui.theme.LemonadableTheme

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadableTheme {
        LemonadeScreen()
    }
}