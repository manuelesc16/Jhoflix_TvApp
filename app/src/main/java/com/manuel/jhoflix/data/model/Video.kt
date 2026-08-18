package com.manuel.jhoflix.data.model

import com.google.gson.annotations.SerializedName

/**
 * Representa un video devuelto por la API de Jhoflix Server.
 */
data class Video(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("videoUrl") val videoUrl: String,
    @SerializedName("posterUrl") val posterUrl: String
)
