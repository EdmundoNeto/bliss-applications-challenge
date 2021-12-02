package com.edmundo.blisschallenge.domain.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edmundo.blisschallenge.general.abstraction.IEmojiEntity

@Entity
data class EmojiEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int? = null,
    @ColumnInfo(name = "url") override val url: String
) : IEmojiEntity
