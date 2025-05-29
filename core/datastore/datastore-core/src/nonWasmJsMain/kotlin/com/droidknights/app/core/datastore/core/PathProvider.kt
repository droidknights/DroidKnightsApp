package com.droidknights.app.core.datastore.core

import okio.Path

expect class PathProvider {

    fun getPath(fileName: String): Path
}
