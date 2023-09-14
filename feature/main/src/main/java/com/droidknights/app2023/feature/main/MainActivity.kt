package com.droidknights.app2023.feature.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.widget.DroidKnightsWidget.Companion.KEY_SESSION_ID
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val sessionIdFromWidget: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra(KEY_SESSION_ID)?.let {
            sessionIdFromWidget.value = it
            intent.removeExtra(KEY_SESSION_ID)
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle(false, this)

            val navigator: MainNavigator = rememberMainNavigator()
            val sessionId = sessionIdFromWidget.collectAsStateWithLifecycle().value

            LaunchedEffect(sessionId) {
                sessionId?.let {
                    navigator.navigateSessionDetail(it)
                }
            }

            KnightsTheme(darkTheme = isDarkTheme) {
                MainScreen(
                    navigator = navigator,
                    onChangeDarkTheme = { isDarkTheme -> viewModel.updateIsDarkTheme(isDarkTheme) }
                )
            }
        }
    }
}
