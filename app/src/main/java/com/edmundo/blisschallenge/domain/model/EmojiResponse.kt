package com.edmundo.blisschallenge.domain.model

import com.edmundo.blisschallenge.domain.deserializer.EmojiJsonDeserializer
import com.edmundo.blisschallenge.general.abstraction.IEmoji
import com.edmundo.blisschallenge.general.abstraction.IEmojiResponse
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = EmojiJsonDeserializer::class)
data class EmojiResponse(
    override val emojiList: List<Emoji>
) : IEmojiResponse {
    data class Emoji(
        override val url: String
    ) : IEmoji
}