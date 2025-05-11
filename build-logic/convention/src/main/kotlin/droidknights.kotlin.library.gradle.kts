import com.droidknights.app.configureKotest
import com.droidknights.app.configureKotlinJvm

plugins {
    kotlin("jvm")
    id("droidknights.verify.detekt")
}

configureKotlinJvm()
configureKotest()
