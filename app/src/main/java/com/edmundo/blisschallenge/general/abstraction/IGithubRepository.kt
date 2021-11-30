package com.edmundo.blisschallenge.general.abstraction

import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    fun getEmojis(): Flow<List<IEmojiEntity>>
    fun getEmojisFromDb(): Flow<List<IEmojiEntity>>
}