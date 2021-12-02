package com.edmundo.blisschallenge.domain.model

import com.edmundo.blisschallenge.general.abstraction.IAvatarResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class AvatarResponse(
    @JsonProperty("avatar_url")
    override val avatarUrl: String? = null,
    @JsonProperty("bio")
    override val bio: Any? = null,
    @JsonProperty("blog")
    override val blog: String? = null,
    @JsonProperty("company")
    override val company: Any? = null,
    @JsonProperty("created_at")
    override val createdAt: String? = null,
    @JsonProperty("email")
    override val email: String? = null,
    @JsonProperty("events_url")
    override val eventsUrl: String? = null,
    @JsonProperty("followers")
    override val followers: Int? = null,
    @JsonProperty("followers_url")
    override val followersUrl: String? = null,
    @JsonProperty("following")
    override val following: Int? = null,
    @JsonProperty("following_url")
    override val followingUrl: String? = null,
    @JsonProperty("gists_url")
    override val gistsUrl: String? = null,
    @JsonProperty("gravatar_id")
    override val gravatarId: String? = null,
    @JsonProperty("hireable")
    override val hireable: Any? = null,
    @JsonProperty("html_url")
    override val htmlUrl: String? = null,
    @JsonProperty("id")
    override val id: Int? = null,
    @JsonProperty("location")
    override val location: String? = null,
    @JsonProperty("login")
    override val login: String? = null,
    @JsonProperty("name")
    override val name: String? = null,
    @JsonProperty("node_id")
    override val nodeId: String? = null,
    @JsonProperty("organizations_url")
    override val organizationsUrl: String? = null,
    @JsonProperty("public_gists")
    override val publicGists: Int? = null,
    @JsonProperty("public_repos")
    override val publicRepos: Int? = null,
    @JsonProperty("received_events_url")
    override val receivedEventsUrl: String? = null,
    @JsonProperty("repos_url")
    override val reposUrl: String? = null,
    @JsonProperty("site_admin")
    override val siteAdmin: Boolean? = null,
    @JsonProperty("starred_url")
    override val starredUrl: String? = null,
    @JsonProperty("subscriptions_url")
    override val subscriptionsUrl: String? = null,
    @JsonProperty("twitter_username")
    override val twitterUsername: Any? = null,
    @JsonProperty("type")
    override val type: String? = null,
    @JsonProperty("updated_at")
    override val updatedAt: String? = null,
    @JsonProperty("url")
    override val url: String? = null
): IAvatarResponse