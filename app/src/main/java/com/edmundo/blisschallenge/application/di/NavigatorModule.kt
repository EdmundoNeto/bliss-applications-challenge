package com.edmundo.blisschallenge.application.di

import com.edmundo.blisschallenge.general.EmojiNavigator
import com.edmundo.blisschallenge.application.MainNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Provides
    @Singleton
    fun provideMainNavigator(): EmojiNavigator {
        return MainNavigator()
    }

}