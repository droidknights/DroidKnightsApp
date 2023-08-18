package com.droidknights.app2023.feature.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle(false, this)
            KnightsTheme(darkTheme = isDarkTheme) {
                MainScreen(
                    onChangeDarkTheme = { isDarkTheme -> viewModel.updateIsDarkTheme(isDarkTheme) }
                )
            }
        }
    }
}
