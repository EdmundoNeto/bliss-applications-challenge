package com.edmundo.blisschallenge.github.di

import com.edmundo.blisschallenge.application.MainNavigator
import com.edmundo.blisschallenge.general.EmojiNavigator
import com.edmundo.blisschallenge.github.adapter.EmojisAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideEmojisAdapter() = EmojisAdapter()
}