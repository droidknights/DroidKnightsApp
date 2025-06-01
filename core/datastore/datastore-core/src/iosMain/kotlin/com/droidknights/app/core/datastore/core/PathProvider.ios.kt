package com.droidknights.app.core.datastore.core

import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class PathProvider {

    @OptIn(ExperimentalForeignApi::class)
    actual fun getPath(fileName: String): Path {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val producePath = requireNotNull(documentDirectory).path + "/$fileName"
        return producePath.toPath()
    }
}
