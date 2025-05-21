package com.droidknights.app.feature.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.Button
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.domain.session.api.usecase.GetSessionsUseCase
import org.koin.compose.LocalKoinApplication

@Composable
internal fun SessionScreen(
    onBackClick: () -> Unit,
    onSessionClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val getSessionsUseCase = LocalKoinApplication.current.get<GetSessionsUseCase>()

    val sessions by getSessionsUseCase.invoke().collectAsStateWithLifecycle(emptyList())
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("SessionScreen")
        Text("$sessions")
        Button(text = "Back", onClick = onBackClick)
        Button(text = "Session Detail (id = 1234)", onClick = { onSessionClick("1234") })
    }
}
