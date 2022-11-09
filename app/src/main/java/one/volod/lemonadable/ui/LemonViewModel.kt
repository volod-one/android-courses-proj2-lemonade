package one.volod.lemonadable.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import one.volod.lemonadable.R
import one.volod.lemonadable.data.LemonUiState


class LemonViewModel : ViewModel() {
    private var _lemonUiState = MutableStateFlow(LemonUiState())
    val lemonUiState: StateFlow<LemonUiState> = _lemonUiState.asStateFlow()

    init {
        setInitialValues()
    }

    private fun setInitialValues() {
        _lemonUiState.update {
            it.copy(
                currentLemon = STARTER_LEMON,
                text = "Tap the lemon tree to select a lemon",
                image = R.drawable.lemon_tree,
                neededClicks = (2..4).random(),
                clickCount = 0
            )
        }
    }

    private fun changeCurrentLemon(currentLemon: Int) {
        when (currentLemon) {
            LEMON_DRINK, STARTER_LEMON -> {
                _lemonUiState.update { it.copy(currentLemon = currentLemon + 1) }
            }
            LEMON_SQUEEZE -> {
                _lemonUiState.update { it.copy(clickCount = it.clickCount + 1) }
                if (lemonUiState.value.clickCount >= lemonUiState.value.neededClicks) {
                    _lemonUiState.update { it.copy(currentLemon = currentLemon + 1) }
                }
            }
            LEMON_RESTART -> setInitialValues()
        }
    }

    private fun updateUi(currentLemon: Int) {
        _lemonUiState.update {
            it.copy(
                text = when (currentLemon) {
                    STARTER_LEMON -> "Tap the lemon tree to select a lemon"
                    LEMON_SQUEEZE -> "Keep tapping the lemon to squeeze it"
                    LEMON_DRINK -> "Tap the lemonade to drink it"
                    else -> "Tap the empty glass to start again"
                },
                image = when (currentLemon) {
                    STARTER_LEMON -> R.drawable.lemon_tree
                    LEMON_SQUEEZE -> R.drawable.lemon_squeeze
                    LEMON_DRINK -> R.drawable.lemon_drink
                    else -> R.drawable.lemon_restart
                }
            )
        }
    }

    fun onClickHandler() {
        changeCurrentLemon(lemonUiState.value.currentLemon) // currentLemon value before mutation
        updateUi(lemonUiState.value.currentLemon) // currentLemon value after mutation
    }

    companion object {
        const val STARTER_LEMON = 0
        const val LEMON_SQUEEZE = 1
        const val LEMON_DRINK = 2
        const val LEMON_RESTART = 3
    }
}