package com.edmundo.blisschallenge.general.abstraction

interface IMapperRoomToApiData<ROOM, APIDATA> {
    fun toApiData(room: ROOM) : APIDATA
}