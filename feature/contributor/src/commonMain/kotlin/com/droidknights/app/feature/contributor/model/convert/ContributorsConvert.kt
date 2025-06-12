package com.droidknights.app.feature.contributor.model.convert

import com.droidknights.app.core.model.contributor.ContributorGroup
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import kotlinx.collections.immutable.toPersistentList

internal fun List<ContributorGroup>.toContributorsUiState(): ContributorsUiState =
    ContributorsUiState.Contributors(
        contributors = flatMap { (year, contributors) ->
            sequenceOf(
                ContributorsUiState.Contributors.Item.Section(title = year.toString()),
            ) + contributors.map { item ->
                ContributorsUiState.Contributors.Item.Contributor(
                    id = item.id,
                    imageUrl = item.imageUrl,
                    githubUrl = item.githubUrl,
                    name = item.name,
                )
            }
        }.toPersistentList(),
    )
