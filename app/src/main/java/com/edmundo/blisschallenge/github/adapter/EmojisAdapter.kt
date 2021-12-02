package com.edmundo.blisschallenge.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.general.abstraction.IEmoji
import com.edmundo.blisschallenge.github.holder.EmojisViewHolder

class EmojisAdapter():
    RecyclerView.Adapter<EmojisViewHolder>() {

    private var emojiList: List<IEmoji> = emptyList()
    private var itemClickAction: ((Int) -> Unit) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojisViewHolder =
        EmojisViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_emoji_viewholder,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EmojisViewHolder, position: Int) {
        val emoji = emojiList[position]

        holder.apply {
            binding(emoji)

            itemView.setOnClickListener {
                itemClickAction?.let {
                    it(position)
                }
            }
        }
        holder.binding(emojiList[position])
    }

    override fun getItemCount(): Int = emojiList.count()

    fun setEmojiList(list: List<IEmoji>) {
        this.emojiList = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        itemClickAction = listener
    }
}