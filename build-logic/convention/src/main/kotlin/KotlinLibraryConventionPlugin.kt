import com.droidknights.app.configureKotest
import com.droidknights.app.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")

                apply("droidknights.verify.detekt")
            }

            configureKotlinJvm()
            configureKotest()
        }
    }
}
