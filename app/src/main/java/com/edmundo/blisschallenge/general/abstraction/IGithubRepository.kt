package com.edmundo.blisschallenge.general.abstraction

import com.edmundo.blisschallenge.domain.model.AvatarResponse
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import com.edmundo.blisschallenge.domain.model.ReposResponseItem

interface IGithubRepository {
    suspend fun getEmojis(): EmojiResponse
    suspend fun getEmojisFromDb(): List<IEmojiEntity>
    suspend fun saveEmojisFromApiToDb(emoji: List<IEmoji>)

    suspend fun getAvatar(login: String): AvatarResponse
    suspend fun getAllAvatarFromDb(): List<IAvatarEntity>
    suspend fun getAvatarUserFromDb(login: String): IAvatarEntity?
    suspend fun saveAvatarFromApiToDb(avatarResponse: IAvatarEntity)
    suspend fun deleteAvatarFromDb(avatarEntity: IAvatarEntity)

    suspend fun getGoogleRepositories(login: String, page: Int, perPage: Int): List<ReposResponseItem>
}