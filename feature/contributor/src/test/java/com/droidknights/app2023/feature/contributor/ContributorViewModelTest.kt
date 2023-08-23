package com.droidknights.app2023.feature.contributor

import app.cash.turbine.test
import com.droidknights.app2023.core.domain.usecase.GetContributorsUseCase
import com.droidknights.app2023.core.model.Contributor
import com.droidknights.app2023.core.testing.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
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
        coEvery { getContributorsUseCase() } returns fakeContributors
        viewModel = ContributorViewModel(getContributorsUseCase)

        // when & then
        viewModel.uiState.test {
            val actual: ContributorsUiState = awaitItem()
            assertIs<ContributorsUiState.Contributors>(actual)
        }
    }

    companion object {
        private val fakeContributors = listOf(
            Contributor(
                name = "test name",
                imageUrl = "test image url",
                githubUrl = "test github url"
            )
        )
    }
}
