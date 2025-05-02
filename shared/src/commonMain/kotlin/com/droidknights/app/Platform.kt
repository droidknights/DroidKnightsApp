package com.droidknights.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform