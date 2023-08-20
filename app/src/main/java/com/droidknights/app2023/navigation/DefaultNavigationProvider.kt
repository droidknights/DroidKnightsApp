package com.droidknights.app2023.navigation

import android.content.Context
import android.content.Intent
import com.droidknights.app2023.core.navigation.NavigationProvider
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultNavigationProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : NavigationProvider {
    override fun toLicense(): Intent {
        return Intent(context, OssLicensesMenuActivity::class.java)
    }
}