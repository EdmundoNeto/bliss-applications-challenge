package com.edmundo.blisschallenge.domain.repository

import com.edmundo.blisschallenge.domain.GithubService
import com.edmundo.blisschallenge.domain.database.dao.EmojiDao
import com.edmundo.blisschallenge.domain.mapper.EmojiMapperApiDataToRoom
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import com.edmundo.blisschallenge.general.abstraction.IEmoji
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import com.edmundo.blisschallenge.general.abstraction.SafeRequest
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val dao: EmojiDao,
    private val service: GithubService
) : IGithubRepository, SafeRequest() {

    override suspend fun getEmojis() = apiRequest {
        service.getEmojis()
    }

    override suspend fun getEmojisFromDb() = dao.getAllEmoji()

    override suspend fun saveEmojisFromApiToDb(emoji: List<IEmoji>) {
        dao.save(emoji.map { EmojiMapperApiDataToRoom.toRoom(it) })
    }
}