package com.droidknights.app.feature.contributor

import app.cash.turbine.test
import com.droidknights.app.core.domain.contributor.usecase.api.GetContributorsUseCase
import com.droidknights.app.core.model.contributor.Contributor
import com.droidknights.app.core.model.contributor.ContributorGroup
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

internal class ContributorViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getContributorsUseCase = mockk<GetContributorsUseCase>()
    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: ContributorViewModel

    @Test
    fun `컨트리뷰터 데이터를 확인할 수 있다`() = runTest {
        // given
        coEvery { getContributorsUseCase() } returns flowOf(fakeContributors)
        viewModel = ContributorViewModel(getContributorsUseCase, navigator)

        // when & then
        viewModel.uiState.test {
            val actual: ContributorsUiState = awaitItem()
            assertIs<ContributorsUiState.Contributors>(actual)
        }
    }

    @Test
    fun `navigateBack이 호출될 때 navigator에게 위임한다`() = runTest {
        // suspend 함수 호출에 대한 stub
        coEvery { navigator.navigateBack() } just Runs
        viewModel = ContributorViewModel(getContributorsUseCase, navigator)

        // when
        viewModel.navigateBack()

        // then
        coVerify(exactly = 1) { navigator.navigateBack() }
    }

    companion object {
        private val fakeContributors = listOf(
            ContributorGroup(
                year = 2024,
                contributors = listOf(
                    Contributor(
                        id = 0L,
                        name = "test name",
                    ),
                ),
            )
        )
    }
}
