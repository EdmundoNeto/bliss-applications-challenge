package com.edmundo.blisschallenge.github.adapter.diffutilcallback

import androidx.recyclerview.widget.DiffUtil
import com.edmundo.blisschallenge.general.abstraction.IReposResponse

class DiffUtilCallBack : DiffUtil.ItemCallback<IReposResponse>() {
    override fun areItemsTheSame(oldItem: IReposResponse, newItem: IReposResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IReposResponse, newItem: IReposResponse): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
    }

}