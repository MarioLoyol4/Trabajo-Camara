package com.example.trabajo_camara.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<Uri?>(null)
    val uiState: StateFlow<Uri?> = _uiState.asStateFlow()

    fun onFotoTomada(uri: Uri?){
        _uiState.update { uri }
    }

    fun onFotoSeleccionada(uri: Uri?){
        _uiState.update { uri }
    }
}