package com.edmundo.blisschallenge.domain.di

import com.edmundo.blisschallenge.domain.mapper.AvatarMapperApiDataToRoom
import com.edmundo.blisschallenge.domain.mapper.EmojiMapperApiDataToRoom
import com.edmundo.blisschallenge.general.abstraction.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideMapperRoomToApiData(): IMapperRoomToApiData<IAvatarEntity, IAvatarResponse> {
        return AvatarMapperApiDataToRoom
    }

    @Provides
    @Singleton
    fun provideMapperApiDataToRoom(): IMapperApiDataToRoom<IAvatarResponse, IAvatarEntity> {
        return AvatarMapperApiDataToRoom
    }

    @Provides
    @Singleton
    fun provideEmojiMapperRoomToApiData(): IMapperRoomToApiData<IEmojiEntity, IEmoji> {
        return EmojiMapperApiDataToRoom
    }

    @Provides
    @Singleton
    fun provideEmojiMapperApiDataToRoom(): IMapperApiDataToRoom<IEmoji, IEmojiEntity> {
        return EmojiMapperApiDataToRoom
    }

}