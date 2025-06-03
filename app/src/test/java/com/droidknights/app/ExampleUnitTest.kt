package com.droidknights.app

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : StringSpec({
    "2 + 2는 4다" {
        4 shouldBe 2 + 2
    }
})
