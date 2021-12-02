package com.edmundo.blisschallenge.general.abstraction

import com.edmundo.blisschallenge.domain.model.EmojiResponse
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    suspend fun getEmojis(): EmojiResponse
    suspend fun getEmojisFromDb(): List<IEmojiEntity>
    suspend fun saveEmojisFromApiToDb(emoji: List<IEmoji>)
}