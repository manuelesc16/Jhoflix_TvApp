package com.manuel.jhoflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.manuel.jhoflix.navigation.JhoflixNavGraph
import com.manuel.jhoflix.ui.theme.JhoflixBackground
import com.manuel.jhoflix.ui.theme.JhoflixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JhoflixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = JhoflixBackground
                ) {
                    JhoflixNavGraph()
                }
            }
        }
    }
}
