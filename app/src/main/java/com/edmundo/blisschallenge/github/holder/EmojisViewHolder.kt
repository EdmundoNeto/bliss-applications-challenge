package com.edmundo.blisschallenge.github.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.domain.database.entity.EmojiEntity
import com.edmundo.blisschallenge.general.abstraction.IEmoji

class EmojisViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(iEmoji: IEmoji) {
        binding.apply {
            setVariable(BR.emojiItem, iEmoji)
        }
    }
}