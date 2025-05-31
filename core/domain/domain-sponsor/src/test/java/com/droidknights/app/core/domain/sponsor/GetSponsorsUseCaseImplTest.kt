package com.droidknights.app.core.domain.sponsor

import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import com.droidknights.app.core.domain.sponsor.usecase.GetSponsorsUseCaseImpl
import com.droidknights.app.core.model.sponsor.Sponsor
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class GetSponsorsUseCaseImplTest {

    private val sponsorRepository = mock<SponsorRepository>()

    private val useCase = GetSponsorsUseCaseImpl(
        sponsorRepository = sponsorRepository,
    )

    @Test
    fun `스폰서들을 priority를 기준 오름차순으로 가져올 수 있다`() = runTest {
        val sponsors = listOf(
            goldSponsorA,
            silverSponsorB,
            platinumSponsorC,
        )

        // priority 값 0(PLATINUM) ~ priority 값 2(SILVER)
        val sortedByPrioritySponsors = listOf(
            platinumSponsorC,
            goldSponsorA,
            silverSponsorB,
        )

        whenever(sponsorRepository.getSponsors()).thenReturn(sponsors)

        Assertions.assertEquals(sortedByPrioritySponsors, useCase.invoke())

        verify(sponsorRepository).getSponsors()
    }

    companion object {
        private val goldSponsorA = Sponsor(
            name = "A",
            imageUrl = "",
            homepage = "",
            grade = Sponsor.Grade.GOLD
        )
        private val silverSponsorB = Sponsor(
            name = "B",
            imageUrl = "",
            homepage = "",
            grade = Sponsor.Grade.SILVER
        )
        private val platinumSponsorC = Sponsor(
            name = "C",
            imageUrl = "",
            homepage = "",
            grade = Sponsor.Grade.PLATINUM
        )
    }
}
