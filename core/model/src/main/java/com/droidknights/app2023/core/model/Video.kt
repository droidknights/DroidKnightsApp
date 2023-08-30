package com.droidknights.app2023.core.model

data class Video(
    val manifestUrl: String,
    val thumbnailUrl: String,
) {
    val isReady = manifestUrl.isNotBlank() && thumbnailUrl.isNotBlank()

    companion object {
        val None = Video("", "")
    }
}