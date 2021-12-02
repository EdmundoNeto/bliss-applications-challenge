package com.edmundo.blisschallenge.domain.mapper

import com.edmundo.blisschallenge.domain.database.entity.EmojiEntity
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import com.edmundo.blisschallenge.general.abstraction.IEmoji
import com.edmundo.blisschallenge.general.abstraction.IEmojiEntity
import com.edmundo.blisschallenge.general.abstraction.IMapperApiDataToRoom
import com.edmundo.blisschallenge.general.abstraction.IMapperRoomToApiData

object EmojiMapperApiDataToRoom : IMapperApiDataToRoom<IEmoji, IEmojiEntity>,
    IMapperRoomToApiData<IEmojiEntity, IEmoji> {
    override fun toRoom(apidata: IEmoji) = EmojiEntity(url = apidata.url)

    override fun toApiData(room: IEmojiEntity) =  EmojiResponse.Emoji(room.url)
}