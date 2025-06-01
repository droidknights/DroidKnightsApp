package com.droidknights.app.core.datastore.core

import okio.Path
import okio.Path.Companion.toPath

actual class PathProvider {

    actual fun getPath(fileName: String): Path = fileName.toPath()
}
