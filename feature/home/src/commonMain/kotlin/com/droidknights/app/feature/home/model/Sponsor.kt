package com.droidknights.app.feature.home.model

import org.jetbrains.compose.resources.DrawableResource

internal sealed class Sponsor {
    data class Organization(
        val tier: Tier,
        val name: String,
        val logoRes: DrawableResource,
        val url: String,
    ) : Sponsor() {
        enum class Tier {
            Platinum, Gold
        }
    }
    data class Individual(
        val name: String,
    ) : Sponsor()
}
