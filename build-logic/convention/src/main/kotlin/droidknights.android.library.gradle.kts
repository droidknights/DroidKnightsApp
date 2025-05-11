import com.droidknights.app.configureCoroutineAndroid
import com.droidknights.app.configureHiltAndroid
import com.droidknights.app.configureKotest
import com.droidknights.app.configureKotlinAndroid
import com.droidknights.app.configureMock

plugins {
    id("com.android.library")
    id("droidknights.verify.detekt")
}

configureKotlinAndroid()
configureKotest()
configureMock()
configureCoroutineAndroid()
configureHiltAndroid()
