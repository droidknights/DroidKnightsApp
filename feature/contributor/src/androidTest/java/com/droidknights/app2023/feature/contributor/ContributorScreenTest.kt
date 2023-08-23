package com.droidknights.app2023.feature.contributor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.droidknights.app2023.core.model.Contributor
import kotlinx.collections.immutable.persistentListOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContributorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val fakeUiState: MutableState<ContributorsUiState> =
        mutableStateOf(ContributorsUiState.Loading)

    @Before
    fun setup() {
        composeTestRule.setContent {
            ContributorScreen(fakeUiState.value, {})
        }
    }

    @Test
    fun 로딩_상태일때는_리스트가_노출되지_않는다() {
        // when
        fakeUiState.value = ContributorsUiState.Loading

        // then
        composeTestRule
            .onNodeWithText("Contributors")
            .assertDoesNotExist()
    }

    @Test
    fun 컨트리뷰터_리스트가_주어지면_화면에_노출한다() {
        // when
        fakeUiState.value = ContributorsUiState.Contributors(
            persistentListOf(
                Contributor(
                    name = "test name",
                    imageUrl = "test image url",
                    githubUrl = "test github url"
                )
            )
        )

        // then
        composeTestRule
            .onNodeWithText("test name")
            .assertExists()
    }
}
