package com.droidknights.app.feature.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.droidknights.app.core.model.sponsor.Sponsor
import com.droidknights.app.feature.home.component.SponsorCard
import com.droidknights.app.feature.home.model.SponsorsUiState
import kotlinx.collections.immutable.persistentListOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SponsorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val fakeUiState: MutableState<SponsorsUiState> =
        mutableStateOf(SponsorsUiState.Loading)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SponsorCard(fakeUiState.value)
        }
    }

    @Test
    fun 로딩_상태일때는_스폰서_리스트가_노출되지_않는다() {
        // when
        fakeUiState.value = SponsorsUiState.Loading

        // then
        composeTestRule
            .onNodeWithText("이 기업들이 후원해요")
            .assertDoesNotExist()
    }

    @Test
    fun 스폰서가_없는_상태일때는_스폰서_리스트가_노출되지_않는다() {
        // when
        fakeUiState.value = SponsorsUiState.Empty

        // then
        composeTestRule
            .onNodeWithText("이 기업들이 후원해요")
            .assertDoesNotExist()
    }

    @Test
    fun 스폰서_리스트가_주어지면_화면에_노출된다() {
        // when
        fakeUiState.value = SponsorsUiState.Sponsors(
            sponsors = sponsors,
        )

        // then
        composeTestRule
            .onNodeWithText("플래티넘 1곳, 골드 1곳, 실버 1곳이\n후원해주셨습니다.")
            .assertExists()
    }

    companion object {
        private val sponsors =
            persistentListOf(
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
                Sponsor(
                    name = "Sponsor3",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.SILVER,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
            )
    }
}
