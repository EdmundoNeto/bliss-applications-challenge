package com.edmundo.blisschallenge.github.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.general.abstraction.IAvatarResponse

class AvatarsViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(iAvatarResponse: IAvatarResponse) {
        binding.apply {
            setVariable(BR.avatarResponse, iAvatarResponse)
        }
    }
}