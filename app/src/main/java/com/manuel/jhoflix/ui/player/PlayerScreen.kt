package com.manuel.jhoflix.ui.player

import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

/**
 * Pantalla de reproducción. Usa Media3 ExoPlayer para hacer streaming
 * directo desde la URL del servidor (no descarga el archivo completo).
 */
@Composable
fun PlayerScreen(
    videoUrl: String,
    videoTitle: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            prepare()
            playWhenReady = true
        }
    }

    // El botón Back del control remoto regresa a la pantalla anterior
    BackHandler(onBack = onBack)

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        factory = { ctx ->
            PlayerView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                player = exoPlayer
                useController = true
                controllerShowTimeoutMs = 3500
                // El propio PlayerView ya soporta D-pad:
                // OK/Enter = play/pause, ← → = retroceder/adelantar (con foco en la barra)
            }
        }
    )
}
