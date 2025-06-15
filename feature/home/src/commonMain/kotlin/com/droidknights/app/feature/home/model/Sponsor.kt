package com.droidknights.app.feature.home.model

import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.sponsor_logo_jetbrains
import droidknights.feature.home.generated.resources.sponsor_logo_koin
import droidknights.feature.home.generated.resources.sponsor_logo_revenue_cat
import org.jetbrains.compose.resources.DrawableResource

internal sealed class Sponsor {
    data class Organization(
        val tier: Tier,
        val name: String,
        val logoRes: DrawableResource,
        val url: String,
    ) : Sponsor() {
        enum class Tier {
            Platinum, Gold, Silver
        }

        companion object {
            val revenueCat = Organization(
                tier = Tier.Platinum,
                name = "RevenueCat",
                logoRes = Res.drawable.sponsor_logo_revenue_cat,
                url = "https://www.revenuecat.com",
            )
            val jetBrains = Organization(
                tier = Tier.Gold,
                name = "JetBrains",
                logoRes = Res.drawable.sponsor_logo_jetbrains,
                url = "https://www.jetbrains.com/",
            )
            val koin = Organization(
                tier = Tier.Silver,
                name = "Koin",
                logoRes = Res.drawable.sponsor_logo_koin,
                url = "https://insert-koin.io/",
            )
        }
    }
    data class Individual(
        val name: String,
    ) : Sponsor()

    companion object {
        val individuals = listOf(
            Individual(name = "경창현"),
            Individual(name = "김태우"),
            Individual(name = "남궁혜인"),
            Individual(name = "박덕성"),
            Individual(name = "심석보"),
            Individual(name = "오홍태"),
            Individual(name = "이성광"),
            Individual(name = "이재일"),
            Individual(name = "이현민"),
            Individual(name = "임태우"),
            Individual(name = "정태훈"),
            Individual(name = "정현아"),
            Individual(name = "최익환"),
            Individual(name = "황은미"),
            Individual(name = "황창훈"),
        )
    }
}
