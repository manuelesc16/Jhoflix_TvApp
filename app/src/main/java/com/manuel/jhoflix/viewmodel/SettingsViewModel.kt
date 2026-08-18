package com.manuel.jhoflix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.manuel.jhoflix.data.local.SettingsDataStore
import com.manuel.jhoflix.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

enum class ConnectionTestState { IDLE, TESTING, SUCCESS, FAILED }

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsDataStore = SettingsDataStore(application)
    private val repository = VideoRepository()

    private val _serverUrl = MutableStateFlow(SettingsDataStore.DEFAULT_URL)
    val serverUrl: StateFlow<String> = _serverUrl

    private val _connectionTestState = MutableStateFlow(ConnectionTestState.IDLE)
    val connectionTestState: StateFlow<ConnectionTestState> = _connectionTestState

    init {
        viewModelScope.launch {
            _serverUrl.value = settingsDataStore.serverUrlFlow.first()
        }
    }

    fun onUrlChanged(newUrl: String) {
        _serverUrl.value = newUrl
        _connectionTestState.value = ConnectionTestState.IDLE
    }

    fun saveUrl() {
        viewModelScope.launch {
            settingsDataStore.saveServerUrl(_serverUrl.value)
        }
    }

    fun testConnection() {
        viewModelScope.launch {
            _connectionTestState.value = ConnectionTestState.TESTING
            val success = repository.testConnection(_serverUrl.value)
            _connectionTestState.value =
                if (success) ConnectionTestState.SUCCESS else ConnectionTestState.FAILED
        }
    }
}
