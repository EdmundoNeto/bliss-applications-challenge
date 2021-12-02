package com.edmundo.blisschallenge.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edmundo.blisschallenge.domain.database.entity.EmojiEntity
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface EmojiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(emojis: List<EmojiEntity>)

    @Query("SELECT * FROM emojientity")
    suspend fun getAllEmoji(): List<EmojiEntity>

}