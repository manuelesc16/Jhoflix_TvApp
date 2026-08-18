package com.manuel.jhoflix.data.repository

import com.manuel.jhoflix.data.model.Video
import com.manuel.jhoflix.data.remote.RetrofitClient

/**
 * Repositorio que abstrae el acceso a los datos de video desde el servidor.
 */
class VideoRepository {

    suspend fun fetchVideos(serverUrl: String): List<Video> {
        val api = RetrofitClient.create(serverUrl)
        return api.getVideos()
    }

    suspend fun testConnection(serverUrl: String): Boolean {
        return try {
            val api = RetrofitClient.create(serverUrl)
            api.getVideos()
            true
        } catch (e: Exception) {
            false
        }
    }
}
