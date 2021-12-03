package com.edmundo.blisschallenge.application.di

import com.edmundo.blisschallenge.general.abstraction.ActivityNavigator
import com.edmundo.blisschallenge.application.abstraction.MainNavigator
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
    fun provideMainNavigator(): ActivityNavigator {
        return MainNavigator()
    }

}