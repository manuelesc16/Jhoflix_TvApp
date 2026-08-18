package com.manuel.jhoflix.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.manuel.jhoflix.ui.home.HomeScreen
import com.manuel.jhoflix.ui.player.PlayerScreen
import com.manuel.jhoflix.ui.settings.SettingsScreen

private object Routes {
    const val HOME = "home"
    const val SETTINGS = "settings"
    const val PLAYER = "player/{videoUrl}/{videoTitle}"
}

@Composable
fun JhoflixNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen(
                onVideoSelected = { video ->
                    val encodedUrl = Uri.encode(video.videoUrl)
                    val encodedTitle = Uri.encode(video.title)
                    navController.navigate("player/$encodedUrl/$encodedTitle")
                },
                onOpenSettings = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }

        composable(
            route = Routes.PLAYER,
            arguments = listOf(
                navArgument("videoUrl") { type = NavType.StringType },
                navArgument("videoTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoUrl = Uri.decode(backStackEntry.arguments?.getString("videoUrl") ?: "")
            val videoTitle = Uri.decode(backStackEntry.arguments?.getString("videoTitle") ?: "")
            PlayerScreen(
                videoUrl = videoUrl,
                videoTitle = videoTitle,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
