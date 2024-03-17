package com.kumcompany.uptime.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.use_cases.UseCases
import com.kumcompany.uptime.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _selectedWatch : MutableStateFlow<Watch?> = MutableStateFlow(null)
    val selectedWatch : StateFlow<Watch?> = _selectedWatch
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val watchId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedWatch.value = watchId?.let {
                useCases.getSelectedWatchUseCase(watchId)
            }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String,String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String,String>> = _colorPalette

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String,String>){
        _colorPalette.value = colors
    }

}

sealed class UiEvent{
    data object GenerateColorPalette : UiEvent()
}