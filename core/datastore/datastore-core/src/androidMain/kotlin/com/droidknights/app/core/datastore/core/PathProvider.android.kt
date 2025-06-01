package com.droidknights.app.core.datastore.core

import android.content.Context
import okio.Path
import okio.Path.Companion.toPath

actual class PathProvider(
    private val context: Context,
) {

    actual fun getPath(fileName: String): Path {
        val producePath = context.filesDir.resolve(fileName).absolutePath
        return producePath.toPath()
    }
}
