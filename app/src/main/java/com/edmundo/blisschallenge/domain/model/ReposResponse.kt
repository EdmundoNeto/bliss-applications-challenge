package com.edmundo.blisschallenge.domain.model

import com.edmundo.blisschallenge.general.abstraction.IReposResponse
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ReposResponseItem(
    @JsonProperty("full_name")
    override val fullName: String,
    @JsonProperty("id")
    override val id: Int,
    @JsonProperty("name")
    override val name: String,
    @JsonProperty("html_url")
    override val htmlUrl: String
) : IReposResponse

