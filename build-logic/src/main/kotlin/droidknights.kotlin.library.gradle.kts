import com.droidknights.app2023.configureKotest
import com.droidknights.app2023.configureKotlin

plugins {
    kotlin("jvm")
    id("droidknights.verify.detekt")
}

configureKotlin()
configureKotest()
