package com.droidknights.app

import org.gradle.api.Project

fun Set<Project>.filterProject(
    body: (target: Project) -> Unit,
) {
    forEach { project ->
        if (project.buildFile.isFile) {
            body(project)
        }
    }
}
