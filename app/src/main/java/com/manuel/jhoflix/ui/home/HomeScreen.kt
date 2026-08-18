package com.manuel.jhoflix.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manuel.jhoflix.data.model.Video
import com.manuel.jhoflix.ui.home.components.MovieCard
import com.manuel.jhoflix.ui.theme.JhoflixAccent
import com.manuel.jhoflix.ui.theme.JhoflixBackground
import com.manuel.jhoflix.ui.theme.JhoflixTextPrimary
import com.manuel.jhoflix.ui.theme.JhoflixTextSecondary
import com.manuel.jhoflix.viewmodel.HomeUiState
import com.manuel.jhoflix.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onVideoSelected: (Video) -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(JhoflixBackground)
            .padding(horizontal = 48.dp, vertical = 32.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Encabezado
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)) {
                Text(
                    text = "JHOFLIX",
                    color = JhoflixAccent,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(modifier = Modifier.align(Alignment.TopEnd)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = onOpenSettings) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Configuración",
                                tint = JhoflixTextPrimary
                            )
                        }
                        IconButton(onClick = { viewModel.loadVideos() }) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Actualizar",
                                tint = JhoflixTextPrimary
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                when (val state = uiState) {
                    is HomeUiState.Loading -> LoadingContent()
                    is HomeUiState.Empty -> EmptyContent()
                    is HomeUiState.Error -> ErrorContent(state.message)
                    is HomeUiState.Success -> MoviesGrid(
                        videos = state.videos,
                        onVideoSelected = onVideoSelected
                    )
                }
            }
        }
    }
}

@Composable
private fun MoviesGrid(videos: List<Video>, onVideoSelected: (Video) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(videos, key = { it.id }) { video ->
            MovieCard(
                video = video,
                onClick = { onVideoSelected(video) }
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = JhoflixAccent)
    }
}

@Composable
private fun EmptyContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "No hay películas disponibles.",
                color = JhoflixTextPrimary,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Agrega videos al servidor y presiona 🔄.",
                color = JhoflixTextSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ErrorContent(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = message,
            color = Color(0xFFFF6B6B),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
    }
}