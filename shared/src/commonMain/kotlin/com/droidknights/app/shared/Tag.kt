package com.droidknights.app.shared

import kotlinx.serialization.Serializable

@Serializable
expect value class Tag(val name: String)
