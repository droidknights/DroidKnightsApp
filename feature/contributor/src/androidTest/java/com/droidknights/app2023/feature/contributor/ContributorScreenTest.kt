package com.droidknights.app2023.feature.contributor

import androidx.activity.ComponentActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.droidknights.app2023.core.domain.model.Contributor
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContributorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val uiState: MutableState<ContributorsUiState> =
        mutableStateOf(ContributorsUiState.Loading)

    @Before
    fun setup() {
        composeTestRule.setContent {
            ContributorScreen(uiState.value)
        }
    }

    @Test
    fun 로딩_상태일때는_리스트가_노출되지_않는다() {
        // when
        uiState.value = ContributorsUiState.Loading

        // then
        composeTestRule
            .onNodeWithText("Contributors")
            .assertDoesNotExist()
    }

    @Test
    fun 컨트리뷰터_리스트가_주어지면_화면에_노출한다() {
        // when
        uiState.value = ContributorsUiState.Contributors(
            listOf(
                Contributor(
                    name = "test name",
                    imageUrl = "test image url"
                )
            )
        )

        // then
        composeTestRule
            .onNodeWithText("test name")
            .assertExists()
    }
}
