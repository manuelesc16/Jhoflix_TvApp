package com.manuel.jhoflix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.manuel.jhoflix.data.local.SettingsDataStore
import com.manuel.jhoflix.data.model.Video
import com.manuel.jhoflix.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Estados posibles de la pantalla Home.
 */
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val videos: List<Video>) : HomeUiState()
    object Empty : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VideoRepository()
    private val settingsDataStore = SettingsDataStore(application)

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadVideos()
    }

    fun loadVideos() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val serverUrl = settingsDataStore.serverUrlFlow.first()
                val videos = repository.fetchVideos(serverUrl)
                _uiState.value = if (videos.isEmpty()) {
                    HomeUiState.Empty
                } else {
                    HomeUiState.Success(videos)
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(
                    "No se pudo conectar con el servidor.\n" +
                        "Verifica que el servidor Jhoflix esté encendido\n" +
                        "y que ambos dispositivos estén conectados a la misma red."
                )
            }
        }
    }
}
