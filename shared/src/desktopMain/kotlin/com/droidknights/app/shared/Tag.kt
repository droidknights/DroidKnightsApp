package com.droidknights.app.shared

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
actual value class Tag(val name: String)