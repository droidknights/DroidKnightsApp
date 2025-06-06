package com.droidknights.app

import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class KnightsAppModuleTest {
    // 모든 모듈은 의존성 누락 없이 검증되어야함
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun appModuleTest() {
        appModule.verify()
    }
}
