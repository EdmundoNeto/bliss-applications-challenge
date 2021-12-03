package com.edmundo.blisschallenge.github.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.general.abstraction.IReposResponse

class ReposViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(iReposResponse: IReposResponse) {
        binding.apply {
            setVariable(BR.reposResponse, iReposResponse)
        }
    }
}