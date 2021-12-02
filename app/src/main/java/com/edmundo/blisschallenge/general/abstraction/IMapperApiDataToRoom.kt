package com.edmundo.blisschallenge.general.abstraction

interface IMapperApiDataToRoom<APIDATA, ROOM> {
    fun toRoom(apidata: APIDATA): ROOM
}