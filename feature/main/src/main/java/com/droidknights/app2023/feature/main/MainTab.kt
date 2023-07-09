package com.droidknights.app2023.feature.main

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
) {
    SETTING(R.drawable.ic_setting, "설정"),
    HOME(R.drawable.ic_home, "홈"),
    TEMP(R.drawable.ic_temp, "임시"),
}
