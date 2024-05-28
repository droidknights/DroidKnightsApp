package com.droidknights.app.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitResponse(
    val commit: Commit,
    val author: AuthorInfo,
)

@Serializable
data class Commit(
    val author: Author,
)

@Serializable
data class Author(
    val name: String,
    val email: String,
    val date: String,
)

@Serializable
data class AuthorInfo(
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val imageUrl: String,
    @SerialName("html_url") val githubUrl: String,
)