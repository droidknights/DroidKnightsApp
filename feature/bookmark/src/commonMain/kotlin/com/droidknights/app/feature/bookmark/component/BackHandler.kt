package com.droidknights.app.feature.bookmark.component

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(enabled: Boolean, onBack: () -> Unit)
