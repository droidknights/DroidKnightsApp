package com.droidknights.app.feature.home

import app.cash.turbine.test
import com.droidknights.app.core.domain.sponsor.usecase.api.GetSponsorsUseCase
import com.droidknights.app.core.model.sponsor.Sponsor
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.contributor.api.RouteContributor
import com.droidknights.app.feature.home.model.SponsorsUiState
import com.droidknights.app.feature.session.api.RouteSession
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

internal class HomeViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSponsorsUseCase = mockk<GetSponsorsUseCase>()
    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: HomeViewModel

    @Test
    fun `후원사 리스트가 비어있다면 후원사 데이터를 확인할 수 없다`() = runTest {
        // given
        coEvery { getSponsorsUseCase() } returns emptyList()
        viewModel = HomeViewModel(getSponsorsUseCase, navigator)

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
        viewModel = HomeViewModel(getSponsorsUseCase, navigator)

        // when & then
        viewModel.sponsorsUiState.test {
            val actual = awaitItem()
            assertIs<SponsorsUiState.Sponsors>(actual)
        }
    }

    @Test
    fun `navigate(RouteSession)가 호출될 때 navigator에게 위임한다`() = runTest {
        // given
        coEvery { navigator.navigate(RouteSession()) } just Runs
        viewModel = HomeViewModel(getSponsorsUseCase, navigator)

        // when
        viewModel.navigateSession()

        // then
        coVerify(exactly = 1) { navigator.navigate(RouteSession()) }
    }

    @Test
    fun `navigate(RouteContributor)가 호출될 때 navigator에게 위임한다`() = runTest {
        // given
        coEvery { navigator.navigate(RouteContributor) } just Runs
        viewModel = HomeViewModel(getSponsorsUseCase, navigator)

        // when
        viewModel.navigateContributor()

        // then
        coVerify(exactly = 1) { navigator.navigate(RouteContributor) }
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
