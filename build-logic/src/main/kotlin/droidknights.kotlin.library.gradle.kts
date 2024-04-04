import com.droidknights.app.configureKotest
import com.droidknights.app.configureKotlin

plugins {
    kotlin("jvm")
    id("droidknights.verify.detekt")
}

configureKotlin()
configureKotest()
