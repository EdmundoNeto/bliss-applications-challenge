package com.edmundo.blisschallenge.github.di

import com.edmundo.blisschallenge.github.adapter.AvatarsAdapter
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

    @Provides
    @Singleton
    fun provideAvatarsAdapter() = AvatarsAdapter()
}