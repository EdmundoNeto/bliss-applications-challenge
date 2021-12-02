package com.edmundo.blisschallenge.domain.mapper

import com.edmundo.blisschallenge.domain.database.entity.AvatarEntity
import com.edmundo.blisschallenge.domain.database.entity.EmojiEntity
import com.edmundo.blisschallenge.domain.model.AvatarResponse
import com.edmundo.blisschallenge.domain.model.EmojiResponse
import com.edmundo.blisschallenge.general.abstraction.*

object AvatarMapperApiDataToRoom : IMapperApiDataToRoom<IAvatarResponse, IAvatarEntity>,
    IMapperRoomToApiData<IAvatarEntity, IAvatarResponse> {
    override fun toRoom(apidata: IAvatarResponse) =
        AvatarEntity(login = apidata.login.orEmpty(), apidata.avatarUrl.orEmpty())

    override fun toApiData(room: IAvatarEntity) = AvatarResponse(login = room.login, avatarUrl = room.url)
}