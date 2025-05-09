package com.droidknights.app.primitive

import com.droidknights.app.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import java.io.File

// https://github.com/DroidKaigi/conference-app-2024/blob/main/build-logic/src/main/kotlin/io/github/droidkaigi/confsched/primitive/AndroidGradleDsl.kt#L81
// setupDetekt
class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.findPlugin("detekt").get().get().pluginId)

            extensions.configure<DetektExtension> {
                buildUponDefaultConfig = true
                parallel = true
                config.setFrom("${project.rootDir}/config/detekt/detekt.yml")
                ignoreFailures = false
                autoCorrect = false
            }

            val reportMerge = if (!rootProject.tasks.names.contains("reportMerge")) {
                // 루트 프로젝트에 병합용 태스크가 없으면 새로 등록
                rootProject.tasks.register("reportMerge", ReportMergeTask::class.java) {
                    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml"))
                }
            } else {
                // 이미 있으면 재사용
                rootProject.tasks.named("reportMerge") as TaskProvider<ReportMergeTask>
            }

            plugins.withType<io.gitlab.arturbosch.detekt.DetektPlugin> {
                tasks.withType<Detekt> detekt@{
                    // 각 Detekt 태스크가 실행된 뒤 reportMerge가 실행되도록 연결 (finalizedBy)
                    finalizedBy(reportMerge)

                    // 분석 대상 파일 설정
                    // precommit일 때, staged된 파일에 대해서만 detekt 실행
                    // ref. https://detekt.dev/docs/gettingstarted/git-pre-commit-hook/#only-run-on-staged-files---gradle
                    source = if (project.hasProperty("precommit")) {
                        getGitStagedFiles(project.rootDir)
                            .map { stagedFiles ->
                                files()
                                    .apply {
                                        setFrom(
                                            *stagedFiles
                                                .filter { it.startsWith(projectDir) }
                                                .toTypedArray()
                                        )
                                    }
                                    .asFileTree
                            }
                            .get()
                    } else {
                        project.files("./").asFileTree
                    }

                    include("**/*.kt")
                    include("**/*.kts")
                    exclude("**/resources/**")
                    exclude("**/build/**")

                    // reportMerge 태스크에 이 모듈의 xmlReportFile을 입력으로 제공
                    reportMerge.configure {
                        input.from(this@detekt.xmlReportFile)
                    }
                }
            }

            dependencies {
                "detektPlugins"(libs.findLibrary("detektFormatting").get())
                "detektPlugins"(libs.findLibrary("twitterComposeRule").get())
            }
        }
    }
}

// ref. https://detekt.dev/docs/gettingstarted/git-pre-commit-hook/#only-run-on-staged-files---gradle
private fun Project.getGitStagedFiles(rootDir: File): Provider<List<File>> {
    return providers.exec {
        commandLine("git", "--no-pager", "diff", "--name-only", "--cached")
    }.standardOutput.asText
        .map { outputText ->
            outputText.trim()
                .split("\n")
                .filter { it.isNotBlank() }
                .map { File(rootDir, it) }
        }
}
