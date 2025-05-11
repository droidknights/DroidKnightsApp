package com.droidknights.app

import org.gradle.api.Project

fun Set<Project>.filterProject(
    body: (target: Project) -> Unit,
) {
    forEach { project ->
        if (project.name != "app" && project.buildFile.isFile) {
            println("-----------")
            println("app ${project.name}")
            println("app ${project.displayName}")
            println("project.buildFile.isFile ${project.buildFile.isFile}")
            println("project.name != \":app\" ${project.name != "app"}")
            body(project)
        }
    }
}
