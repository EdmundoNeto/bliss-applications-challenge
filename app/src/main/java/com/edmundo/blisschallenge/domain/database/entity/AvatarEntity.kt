package com.edmundo.blisschallenge.domain.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edmundo.blisschallenge.general.abstraction.IAvatarEntity

@Entity(tableName = "avatar")
data class AvatarEntity(
    @PrimaryKey
    override val login: String,
    override val url: String
): IAvatarEntity
