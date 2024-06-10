package com.droidknights.app.feature.contributor.model.convert

import com.droidknights.app.core.model.Contributor
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import kotlinx.collections.immutable.toPersistentList

internal fun Map<Int, List<Contributor>>.toContributorsUiState(): ContributorsUiState {
    val newList = mutableListOf<ContributorsUiState.Contributors.Item>()
    forEach { (key, values) ->
        newList.add(
            ContributorsUiState.Contributors.Item.Section(
                title = key.toString(),
            )
        )
        values.forEach { item ->
            newList.add(
                ContributorsUiState.Contributors.Item.User(
                    id = item.id,
                    imageUrl = item.imageUrl,
                    githubUrl = item.githubUrl,
                    name = item.name,
                )
            )
        }
    }
    return ContributorsUiState.Contributors(
        contributors = newList.toPersistentList(),
    )
}