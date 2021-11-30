package com.edmundo.blisschallenge.domain.repository

import android.util.Log
import com.edmundo.blisschallenge.domain.GithubService
import com.edmundo.blisschallenge.domain.database.dao.EmojiDao
import com.edmundo.blisschallenge.domain.database.entity.EmojiEntity
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val dao: EmojiDao,
    private val service: GithubService
) : IGithubRepository {

    override fun getEmojis(): Flow<List<EmojiEntity>> {
        CoroutineScope(IO).launch {
            try {
                service.getEmojis().body()?.let {
                    val emojisDb =
                        it.emojiList.mapIndexed { index, emoji -> EmojiEntity(index, emoji.url) }
                    dao.save(emojisDb)
                }
            } catch (e: Exception) {
                Log.e("GithubRepository", "getEmojis: ${e.message}")
            }
        }
        return dao.getAllEmoji()
    }

    override fun getEmojisFromDb() = dao.getAllEmoji()

}