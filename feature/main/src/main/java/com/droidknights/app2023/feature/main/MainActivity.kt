package com.droidknights.app2023.feature.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavController
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavGraph
import com.droidknights.app2023.feature.contributor.api.ContributorNavController
import com.droidknights.app2023.feature.contributor.api.ContributorNavGraph
import com.droidknights.app2023.feature.home.api.HomeNavController
import com.droidknights.app2023.feature.home.api.HomeNavGraph
import com.droidknights.app2023.feature.session.api.SessionDetailNavController
import com.droidknights.app2023.feature.session.api.SessionNavController
import com.droidknights.app2023.feature.session.api.SessionNavGraph
import com.droidknights.app2023.feature.setting.api.SettingNavController
import com.droidknights.app2023.feature.setting.api.SettingNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var homeNavGraph: HomeNavGraph

    @Inject
    lateinit var bookmarkNavGraph: BookmarkNavGraph

    @Inject
    lateinit var contributorNavGraph: ContributorNavGraph

    @Inject
    lateinit var sessionNavGraph: SessionNavGraph

    @Inject
    lateinit var settingNavGraph: SettingNavGraph

    @Inject
    lateinit var mainTabs: MainTabs

    @Inject
    internal lateinit var mainNavigatorFactory: MainNavigator.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle(false, this)
            KnightsTheme(darkTheme = isDarkTheme) {
                val navigator = rememberMainNavigator(
                    rememberNavController(),
                    mainNavigatorFactory
                )
                MainScreen(
                    navigator = navigator,
                    onChangeDarkTheme = { isDarkTheme -> viewModel.updateIsDarkTheme(isDarkTheme) },
                    homeNavGraph,
                    bookmarkNavGraph,
                    contributorNavGraph,
                    sessionNavGraph,
                    settingNavGraph,
                    mainTabs
                )
            }
        }
    }
}
