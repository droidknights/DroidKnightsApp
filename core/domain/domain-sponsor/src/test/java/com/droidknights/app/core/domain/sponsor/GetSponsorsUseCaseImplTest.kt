package com.droidknights.app.core.domain.sponsor

import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import com.droidknights.app.core.model.Sponsor
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
    fun `test invoke`() = runTest {
        val mockItem = listOf(
            Sponsor.Default.copy(
                name = "name",
            ),
            Sponsor.Default.copy(
                name = "name two",
            ),
        )
        whenever(sponsorRepository.getSponsors()).thenReturn(mockItem)

        Assertions.assertEquals(mockItem, useCase.invoke())

        verify(sponsorRepository).getSponsors()
    }
}
