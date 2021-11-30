package com.edmundo.blisschallenge.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AvatarResponse(
    @JsonProperty("avatar_url")
    val avatarUrl: String?,
    @JsonProperty("bio")
    val bio: Any?,
    @JsonProperty("blog")
    val blog: String?,
    @JsonProperty("company")
    val company: Any?,
    @JsonProperty("created_at")
    val createdAt: String?,
    @JsonProperty("email")
    val email: String?,
    @JsonProperty("events_url")
    val eventsUrl: String?,
    @JsonProperty("followers")
    val followers: Int?,
    @JsonProperty("followers_url")
    val followersUrl: String?,
    @JsonProperty("following")
    val following: Int?,
    @JsonProperty("following_url")
    val followingUrl: String?,
    @JsonProperty("gists_url")
    val gistsUrl: String?,
    @JsonProperty("gravatar_id")
    val gravatarId: String?,
    @JsonProperty("hireable")
    val hireable: Any?,
    @JsonProperty("html_url")
    val htmlUrl: String?,
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("location")
    val location: String?,
    @JsonProperty("login")
    val login: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("node_id")
    val nodeId: String?,
    @JsonProperty("organizations_url")
    val organizationsUrl: String?,
    @JsonProperty("public_gists")
    val publicGists: Int?,
    @JsonProperty("public_repos")
    val publicRepos: Int?,
    @JsonProperty("received_events_url")
    val receivedEventsUrl: String?,
    @JsonProperty("repos_url")
    val reposUrl: String?,
    @JsonProperty("site_admin")
    val siteAdmin: Boolean?,
    @JsonProperty("starred_url")
    val starredUrl: String?,
    @JsonProperty("subscriptions_url")
    val subscriptionsUrl: String?,
    @JsonProperty("twitter_username")
    val twitterUsername: Any?,
    @JsonProperty("type")
    val type: String?,
    @JsonProperty("updated_at")
    val updatedAt: String?,
    @JsonProperty("url")
    val url: String?
)