import com.droidknights.app.configureHiltAndroid
import com.droidknights.app.configureKotestAndroid
import com.droidknights.app.configureKotlinAndroid
import com.droidknights.app.configureRoborazzi

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
configureRoborazzi()
