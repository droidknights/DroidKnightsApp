package com.droidknights.app.feature.contributor

import app.cash.turbine.test
import com.droidknights.app.core.domain.usecase.GetContributorsUseCase
import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorGroup
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

internal class ContributorViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getContributorsUseCase: GetContributorsUseCase = mockk()
    private lateinit var viewModel: ContributorViewModel

    @Test
    fun `컨트리뷰터 데이터를 확인할 수 있다`() = runTest {
        // given
        coEvery { getContributorsUseCase() } returns flowOf(fakeContributors)
        viewModel = ContributorViewModel(getContributorsUseCase)

        // when & then
        viewModel.uiState.test {
            val actual: ContributorsUiState = awaitItem()
            assertIs<ContributorsUiState.Contributors>(actual)
        }
    }

    companion object {
        private val fakeContributors = listOf(
            ContributorGroup(
                year = 2024,
                contributors = listOf(
                    Contributor(
                        id = 0L,
                        name = "test name",
                        imageUrl = "test image url",
                        githubUrl = "test github url",
                    ),
                ),
            )
        )
    }
}
