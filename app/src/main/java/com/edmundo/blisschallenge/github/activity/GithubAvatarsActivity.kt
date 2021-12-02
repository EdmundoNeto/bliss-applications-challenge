package com.edmundo.blisschallenge.github.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.general.extensions.bindingContentView
import com.edmundo.blisschallenge.general.extensions.observe
import com.edmundo.blisschallenge.github.adapter.AvatarsAdapter
import com.edmundo.blisschallenge.github.viewmodel.GithubAvatarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_emojis.*
import javax.inject.Inject

@AndroidEntryPoint
class GithubAvatarsActivity : AppCompatActivity() {

    private val viewModel by viewModels<GithubAvatarViewModel>()

    @Inject
    lateinit var avatarsAdapter: AvatarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentView(R.layout.activity_avatars).run {
            setVariable(BR.githubAvatarViewModel, viewModel)
            lifecycleOwner = this@GithubAvatarsActivity
        }

        setupView()
    }

    private fun setupView() {
        avatarsAdapter.setOnItemClickListener { iAvatarResponse, position ->
            viewModel.removeAvatarFromList(iAvatarResponse)
        }
    }

    private fun setupObservable() {
        viewModel.getAvatarList()

        viewModel.run {
            observe(githubAvatarList) {
                it?.run {
                    avatarsAdapter.setAvatarList(this)
                }
            }
        }
    }

    private fun setupRecycler() {
        main.apply {
            this.adapter = avatarsAdapter
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