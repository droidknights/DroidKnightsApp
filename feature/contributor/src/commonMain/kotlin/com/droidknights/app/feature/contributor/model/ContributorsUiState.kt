package com.droidknights.app.feature.contributor.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Stable
sealed interface ContributorsUiState {
    @Immutable
    data object Loading : ContributorsUiState

    @Immutable
    data class Contributors(
        val contributors: ImmutableList<Contributor>,
    ) : ContributorsUiState {
        @Immutable
        data class Contributor(
            val id: Long,
            val name: String,
            val imageUrl: String,
            val githubUrl: String,
        ) {
            companion object {
                val Default =
                    Contributor(
                        id = -1,
                        name = "",
                        imageUrl = "",
                        githubUrl = "",
                    )
                val Dummy =
                    Contributor(
                        id = -1,
                        name = "Username",
                        imageUrl = "https://avatars.githubusercontent.com/u/69571848?v=4",
                        githubUrl = "https://github.com/junjange",
                    )
            }
        }

        companion object {
            val DummyData = Contributors(List(10) { Contributor.Dummy }.toPersistentList())
        }
    }
}
