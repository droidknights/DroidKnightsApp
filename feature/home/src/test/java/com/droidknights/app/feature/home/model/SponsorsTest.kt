package com.droidknights.app.feature.home.model

import com.droidknights.app.core.model.sponsor.Sponsor
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test
import kotlin.test.assertEquals

class SponsorsTest {

    @Test
    fun `각 등급에 해당하는 스폰서들의 수를 가져올 수 있다`() {
        // given
        val uiState = SponsorsUiState.Sponsors(mockSponsors)

        // when
        val actualPlatinumCount: Int = uiState.platinumCount
        val actualGoldCount: Int = uiState.goldCount
        val actualSilverCount: Int = uiState.silverCount

        // then
        assertEquals(expected = 1, actual = actualPlatinumCount)
        assertEquals(expected = 2, actual = actualGoldCount)
        assertEquals(expected = 2, actual = actualSilverCount)
    }

    @Test
    fun `각 등급의 우선순위에 따라 그룹핑된 스폰서들을 가져올 수 있다`() {
        // given
        val uiState = SponsorsUiState.Sponsors(mockSponsors)

        // when
        val actual: ImmutableList<List<Sponsor>> = uiState.groupedSponsorsByGrade

        // then
        assertEquals(
            expected = persistentListOf(
                // Grade.PLATINUM
                listOf(
                    mockSponsorE,
                ),
                // Grade.GOLD
                listOf(
                    mockSponsorB,
                    mockSponsorD,
                ),
                // Grade.SILVER
                listOf(
                    mockSponsorA,
                    mockSponsorC,
                )
            ),
            actual = actual,
        )
    }

    @Test
    fun `각 등급 중 빠진 등급이 있어도 존재하는 등급들을 기준으로 스폰서들을 가져올 수 있다`() {
        // given
        val mockSponsorsWithoutGoldGrade = persistentListOf(
            mockSponsorA,
            mockSponsorC,
            mockSponsorE,
        )
        val uiState = SponsorsUiState.Sponsors(mockSponsorsWithoutGoldGrade)

        // when
        val actual: ImmutableList<List<Sponsor>> = uiState.groupedSponsorsByGrade

        // then
        assertEquals(
            expected = persistentListOf(
                listOf(
                    mockSponsorE,
                ),
                listOf(
                    mockSponsorA,
                    mockSponsorC,
                )
            ),
            actual = actual,
        )
    }

    companion object {
        private val mockSponsorA = Sponsor(
            name = "A회사",
            imageUrl = "https://example.com/a.png",
            homepage = "https://a.com",
            grade = Sponsor.Grade.SILVER
        )
        private val mockSponsorB = Sponsor(
            name = "B회사",
            imageUrl = "https://example.com/b.png",
            homepage = "https://b.com",
            grade = Sponsor.Grade.GOLD
        )
        private val mockSponsorC = Sponsor(
            name = "C회사",
            imageUrl = "https://example.com/c.png",
            homepage = "https://c.com",
            grade = Sponsor.Grade.SILVER
        )
        private val mockSponsorD = Sponsor(
            name = "D회사",
            imageUrl = "https://example.com/d.png",
            homepage = "https://d.com",
            grade = Sponsor.Grade.GOLD
        )
        private val mockSponsorE = Sponsor(
            name = "E회사",
            imageUrl = "https://example.com/e.png",
            homepage = "https://e.com",
            grade = Sponsor.Grade.PLATINUM
        )
        val mockSponsors = persistentListOf(
            mockSponsorA,
            mockSponsorB,
            mockSponsorC,
            mockSponsorD,
            mockSponsorE,
        )
    }
}
