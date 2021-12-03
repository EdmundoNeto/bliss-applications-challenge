package com.edmundo.blisschallenge.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.general.abstraction.IReposResponse
import com.edmundo.blisschallenge.github.adapter.diffutilcallback.DiffUtilCallBack
import com.edmundo.blisschallenge.github.holder.ReposViewHolder

class ReposAdapter():
    PagedListAdapter<IReposResponse, ReposViewHolder>(DiffUtilCallBack()) {

    private var itemClickAction: ((String) -> Unit) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder =
        ReposViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_repos_viewholder,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val repo = getItem(position)

        repo?.let {
            holder.apply {
                binding(it)

                itemView.setOnClickListener {
                    itemClickAction?.let {
                        it(repo.htmlUrl)
                    }
                }
            }
            holder.binding(it)
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        itemClickAction = listener
    }
}