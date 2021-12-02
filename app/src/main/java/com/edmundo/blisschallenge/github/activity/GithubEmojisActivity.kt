package com.edmundo.blisschallenge.github.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.general.extensions.bindingContentView
import com.edmundo.blisschallenge.general.extensions.observe
import com.edmundo.blisschallenge.github.adapter.EmojisAdapter
import com.edmundo.blisschallenge.github.viewmodel.GithubEmojiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_emojis.*
import javax.inject.Inject

@AndroidEntryPoint
class GithubEmojisActivity : AppCompatActivity() {

    private val viewModel by viewModels<GithubEmojiViewModel>()

    @Inject
    lateinit var emojisAdapter: EmojisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentView(R.layout.activity_emojis).run {
            setVariable(BR.githubEmojiViewModel, viewModel)
            lifecycleOwner = this@GithubEmojisActivity
        }

        setupView()
    }

    private fun setupView() {
        emojisAdapter.setOnItemClickListener {
            emojisAdapter.notifyItemRemoved(it)
        }

        srRefreshList.setOnRefreshListener {
            viewModel.getEmojiList()
        }
    }

    private fun setupObservable() {
        viewModel.getEmojiList()

        viewModel.run {
            observe(githubEmojiList) {
                it?.run {
                    emojisAdapter.setEmojiList(this)
                    finishRefreshing()
                }
            }
        }
    }

    private fun finishRefreshing() {
        with(srRefreshList) {
            if(isRefreshing)
                isRefreshing = false
        }
    }

    private fun setupRecycler() {
        main.apply {
            this.adapter = emojisAdapter
            layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupObservable()
    }

}