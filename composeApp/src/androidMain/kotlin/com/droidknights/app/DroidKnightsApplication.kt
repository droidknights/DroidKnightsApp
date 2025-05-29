package com.droidknights.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DroidKnightsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            knightsAppDeclaration {
                androidContext(this@DroidKnightsApplication)
            },
        )
    }
}
