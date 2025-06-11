package com.droidknights.app.feature.home.model

import androidx.annotation.DrawableRes

internal sealed class Sponsor {
    data class Organization(
        val tier: Tier,
        val name: String,
        @DrawableRes
        val logoRes: Int,
        val url: String,
    ) : Sponsor() {
        enum class Tier {
            Platinum, Gold
        }
    }
    data class Individual(
        val name: String
    ) : Sponsor()
}