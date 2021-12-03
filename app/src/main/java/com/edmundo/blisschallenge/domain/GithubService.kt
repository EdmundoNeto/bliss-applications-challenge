package com.edmundo.blisschallenge.domain

import com.edmundo.blisschallenge.domain.model.AvatarResponse
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import com.edmundo.blisschallenge.domain.model.ReposResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("emojis")
    suspend fun getEmojis(): Response<EmojiResponse>

    @GET("users/{login}")
    suspend fun getAvatar(@Path("login") login: String): Response<AvatarResponse>

    @GET("users/{login}/repos")
    suspend fun getGoogleRepositories(
        @Path("login") login: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<ReposResponseItem>>

}