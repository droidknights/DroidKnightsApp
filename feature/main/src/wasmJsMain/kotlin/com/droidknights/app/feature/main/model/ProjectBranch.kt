package com.droidknights.app.feature.main.model

import kotlinx.collections.immutable.persistentListOf

internal data class ProjectBranch(
    val name: String,
    val tag: String,
    val url: String,
)

internal val branches2025 = persistentListOf(
    ProjectBranch(
        name = "2025/app",
        tag = "Architecture",
        url = "https://github.com/droidknights/DroidKnightsApp/tree/2025/app",
    ),
    ProjectBranch(
        name = "2025/compose-multiplatform",
        tag = "Multiplatform",
        url = "https://github.com/droidknights/DroidKnightsApp/tree/2025/compose-multiplatform",
    ),
)
