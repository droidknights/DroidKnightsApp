package com.droidknights.app2023.feature.home

import app.cash.turbine.test
import com.droidknights.app2023.core.domain.usecase.GetSponsorsUseCase
import com.droidknights.app2023.core.model.Sponsor
import com.droidknights.app2023.core.testing.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

internal class HomeViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSponsorsUseCase: GetSponsorsUseCase = mockk()
    private lateinit var viewModel: HomeViewModel

    @Test
    fun `후원사 리스트가 비어있다면 후원사 데이터를 확인할 수 없다`() = runTest {
        // given
        coEvery { getSponsorsUseCase() } returns emptyList()
        viewModel = HomeViewModel(getSponsorsUseCase)

        // when & then
        viewModel.sponsorsUiState.test {
            val actual = awaitItem()
            assertIs<SponsorsUiState.Empty>(actual)
        }
    }

    @Test
    fun `후원사 리스트가 존재한다면 후원사 데이터를 확인할 수 있다`() = runTest {
        // given
        coEvery { getSponsorsUseCase() } returns fakeSponsors
        viewModel = HomeViewModel(getSponsorsUseCase)

        // when & then
        viewModel.sponsorsUiState.test {
            val actual = awaitItem()
            assertIs<SponsorsUiState.Sponsors>(actual)
        }
    }

    companion object {
        private val fakeSponsors =
            listOf(
                Sponsor(
                    name = "Sponsor1",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.GOLD,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
                Sponsor(
                    name = "Sponsor2",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.PLATINUM,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
            )
    }
}
