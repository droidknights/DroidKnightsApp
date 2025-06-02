package com.droidknights.app

import com.droidknights.app.core.data.session.di.coreDataSessionModule
import com.droidknights.app.core.data.setting.di.coreDataSettingModule
import com.droidknights.app.core.datastore.core.di.coreDatastoreCoreModules
import com.droidknights.app.core.datastore.session.di.coreDatastoreSessionModule
import com.droidknights.app.core.datastore.settings.di.coreDatastoreSettingsModule
import com.droidknights.app.core.domain.session.di.coreDomainSessionModule
import com.droidknights.app.feature.session.di.featureSessionModule
import com.droidknights.app.feature.setting.di.featureSettingModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class KnightsAppModuleTest : KoinTest {

    // 모듈 세팅
    private val appModule = module {
        viewModelOf(::AppViewModel)
    }
    private val coreDataModules = listOf(
        coreDataSettingModule,
        coreDataSessionModule,
    )
    private val coreDatastoreModules = listOf(
        coreDatastoreSessionModule,
        coreDatastoreSettingsModule,
    ) + coreDatastoreCoreModules
    private val coreDomainModules = listOf(
        coreDomainSessionModule,
    )
    private val featureModules = listOf(
        featureSessionModule,
        featureSettingModule,
    )

    // 모든 모듈은 의존성 누락 없이 검증되어야함
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun allModulesShouldBeVerifiedWithoutMissingDependencies() {
        val checkModules = module {
            appModule
            coreDataModules
            coreDatastoreModules
            coreDomainModules
            featureModules
        }

        checkModules.verify(
            // 외부에서 주입해야 하는 타입을 명시
            extraTypes = listOf(
                AppViewModel::class,
            ),
        )
    }
}
