package Model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StateViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(SaveModel())
    val uiState: StateFlow<SaveModel> = _uiState.asStateFlow()


    public fun Set(place: Place){
        _uiState.update { currentState ->
            currentState.copy(currentPlace = place)
        }
    }

    public fun Get(): Place {
        return uiState.value.currentPlace
    }
}