package com.droidknights.app2023.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
    val lifecycleEvent = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }

    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleEvent.value = event
        }
        this@observeAsState.addObserver(observer)
        onDispose {
            this@observeAsState.removeObserver(observer)
        }
    }
    return lifecycleEvent
}
