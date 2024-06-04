package com.droidknights.app.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val sessionIdFromWidget: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
