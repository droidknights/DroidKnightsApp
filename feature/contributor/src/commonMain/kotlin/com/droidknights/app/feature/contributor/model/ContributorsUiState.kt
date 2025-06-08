package com.droidknights.app.feature.contributor.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
sealed interface ContributorsUiState {
    @Immutable
    data object Loading : ContributorsUiState

    @Immutable
    data class Contributors(
        val contributors: ImmutableList<Item>,
    ) : ContributorsUiState {

        @Stable
        sealed interface Item {

            @Immutable
            data class Section(
                val title: String,
            ) : Item

            @Immutable
            data class Contributor(
                val id: Long,
                val name: String,
                val imageUrl: String,
                val githubUrl: String,
            ) : Item {
                companion object {
                    val Default =
                        Contributor(
                            id = -1,
                            name = "",
                            imageUrl = "",
                            githubUrl = "",
                        )
                }
            }
        }
    }
}
