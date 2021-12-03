package com.edmundo.blisschallenge.github.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.general.extensions.bindingContentView
import com.edmundo.blisschallenge.general.extensions.observe
import com.edmundo.blisschallenge.github.adapter.ReposAdapter
import com.edmundo.blisschallenge.github.viewmodel.GithubReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_emojis.*
import javax.inject.Inject

@AndroidEntryPoint
class GithubReposActivity: AppCompatActivity() {

    private val viewModel by viewModels<GithubReposViewModel>()

    @Inject
    lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentView(R.layout.activity_repos).run {
            lifecycleOwner = this@GithubReposActivity
        }

        setupView()
    }

    private fun setupObservable() {
        viewModel.getGithubRepos()

        viewModel.run {
            observe(reposLiveData) {
                it?.run {
                    reposAdapter.submitList(this)
                }
            }
        }
    }

    private fun setupView() {
        reposAdapter.setOnItemClickListener {
            openRepoPage(it)
        }
    }

    private fun openRepoPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun setupRecycler() {
        main.apply {
            this.adapter = reposAdapter
            layoutManager = LinearLayoutManager(this@GithubReposActivity)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupObservable()
    }

}