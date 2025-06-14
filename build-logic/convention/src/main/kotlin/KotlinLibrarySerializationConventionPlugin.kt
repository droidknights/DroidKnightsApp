import com.droidknights.app.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibrarySerializationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlinx-serialization")
            }

            dependencies {
                "implementation"(findLibrary("kotlinx-serialization-json"))
            }
        }
    }
}
