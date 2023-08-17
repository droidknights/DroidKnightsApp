import com.droidknights.app2023.configureCoroutineAndroid
import com.droidknights.app2023.configureHiltAndroid
import com.droidknights.app2023.configureKotest
import com.droidknights.app2023.configureKotlinAndroid

plugins {
    id("com.android.library")
    id("droidknights.verify.detekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
