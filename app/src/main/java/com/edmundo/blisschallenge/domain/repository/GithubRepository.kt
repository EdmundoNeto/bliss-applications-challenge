package com.edmundo.blisschallenge.domain.repository

import com.edmundo.blisschallenge.domain.GithubService
import com.edmundo.blisschallenge.domain.database.dao.AvatarDao
import com.edmundo.blisschallenge.domain.database.dao.EmojiDao
import com.edmundo.blisschallenge.domain.database.entity.AvatarEntity
import com.edmundo.blisschallenge.domain.mapper.AvatarMapperApiDataToRoom
import com.edmundo.blisschallenge.domain.mapper.EmojiMapperApiDataToRoom
import com.edmundo.blisschallenge.general.abstraction.*
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val emojiDao: EmojiDao,
    private val avatarDao: AvatarDao,
    private val service: GithubService
) : IGithubRepository, SafeRequest() {

    override suspend fun getEmojis() = apiRequest {
        service.getEmojis()
    }

    override suspend fun getEmojisFromDb() = emojiDao.getAllEmoji()

    override suspend fun saveEmojisFromApiToDb(emoji: List<IEmoji>) {
        emojiDao.save(emoji.map { EmojiMapperApiDataToRoom.toRoom(it) })
    }

    override suspend fun getAvatar(login: String) = apiRequest {
        service.getAvatar(login)
    }

    override suspend fun getAllAvatarFromDb() = avatarDao.getAllAvatar()

    override suspend fun saveAvatarFromApiToDb(avatarResponse: IAvatarEntity) {
        avatarDao.insertUserAvatar(AvatarEntity(login = avatarResponse.login, url = avatarResponse.url))
    }

    override suspend fun deleteAvatarFromDb(avatarEntity: IAvatarEntity) {
        avatarDao.deleteUserAvatar(AvatarEntity(login = avatarEntity.login, url = avatarEntity.url))
    }

    override suspend fun getAvatarUserFromDb(login: String) = avatarDao.getUserAvatar(login)

}