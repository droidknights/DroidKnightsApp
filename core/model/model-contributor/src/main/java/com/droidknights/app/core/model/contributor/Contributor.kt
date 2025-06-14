package com.droidknights.app.core.model.contributor

data class Contributor(
    val id: Long,
    val name: String,
) {

    val imageUrl: String =
        "https://avatars.githubusercontent.com/u/$id?v=4"

    val githubUrl: String =
        "https://github.com/$name"
}
