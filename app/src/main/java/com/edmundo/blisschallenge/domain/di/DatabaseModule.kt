package com.edmundo.blisschallenge.domain.di

import android.content.Context
import androidx.room.Room
import com.edmundo.blisschallenge.domain.database.AppDatabase
import com.edmundo.blisschallenge.domain.database.dao.AvatarDao
import com.edmundo.blisschallenge.domain.database.dao.EmojiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "bliss.db"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideEmojiDao(db: AppDatabase): EmojiDao {
        return db.getEmojiDao()
    }

    @Provides
    fun provideAvatarDao(db: AppDatabase): AvatarDao {
        return db.getAvatarDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

}