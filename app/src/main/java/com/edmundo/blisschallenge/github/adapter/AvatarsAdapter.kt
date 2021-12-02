package com.edmundo.blisschallenge.github.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.general.abstraction.IAvatarResponse
import com.edmundo.blisschallenge.github.holder.AvatarsViewHolder

class AvatarsAdapter():
    RecyclerView.Adapter<AvatarsViewHolder>() {

    private var avatarList = arrayListOf<IAvatarResponse>()
    private var itemClickAction: ((IAvatarResponse, Int) -> Unit) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarsViewHolder =
        AvatarsViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_avatar_viewholder,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AvatarsViewHolder, position: Int) {
        val avatar = avatarList[position]

        holder.apply {
            binding(avatar)

            itemView.setOnClickListener {
                itemClickAction?.let {
                    it(avatar, position)
                }
                removeItem(position)
            }
        }
        holder.binding(avatarList[position])
    }

    override fun getItemCount(): Int = avatarList.count()

    @SuppressLint("NotifyDataSetChanged")
    private fun removeItem(position: Int) {
        this.avatarList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAvatarList(list: List<IAvatarResponse>) {
        this.avatarList = arrayListOf()
        this.avatarList.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (IAvatarResponse, Int) -> Unit) {
        itemClickAction = listener
    }
}