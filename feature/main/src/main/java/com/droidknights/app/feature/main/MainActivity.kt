package com.droidknights.app.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.widget.DroidKnightsWidget.Companion.KEY_SESSION_ID
import com.droidknights.app.widget.sendWidgetUpdateCommand
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val sessionIdFromWidget: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // widget 처리를 위함
        sendWidgetUpdateCommand(application)

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
