package com.edmundo.blisschallenge.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edmundo.blisschallenge.domain.database.dao.AvatarDao
import com.edmundo.blisschallenge.domain.database.dao.EmojiDao
import com.edmundo.blisschallenge.domain.database.entity.AvatarEntity
import com.edmundo.blisschallenge.domain.database.entity.EmojiEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [EmojiEntity::class, AvatarEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEmojiDao(): EmojiDao
    abstract fun getAvatarDao(): AvatarDao

}