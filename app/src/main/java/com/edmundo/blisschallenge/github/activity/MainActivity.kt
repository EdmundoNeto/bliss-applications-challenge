package com.edmundo.blisschallenge.github.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.general.abstraction.ActivityNavigator
import com.edmundo.blisschallenge.general.extensions.bindingContentView
import com.edmundo.blisschallenge.general.extensions.observe
import com.edmundo.blisschallenge.general.extensions.setThumbnail
import com.edmundo.blisschallenge.github.viewmodel.GithubAvatarViewModel
import com.edmundo.blisschallenge.github.viewmodel.GithubEmojiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<GithubEmojiViewModel>()
    private val avatarViewModel by viewModels<GithubAvatarViewModel>()
    @Inject
    lateinit var activityNavigator: ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingContentView(R.layout.activity_main).run {
            setVariable(BR.githubEmojiViewModel, viewModel)
            lifecycleOwner = this@MainActivity
        }

        setupObservable()
        setupView()
    }

    private fun setupObservable() {
        viewModel.getEmojiList()

        avatarViewModel.run {
            observe(githubUserAvatar) {
                it?.run {
                    ivEmoji.setThumbnail(this)
                }
            }
        }
    }

    private fun setupView() {

        btnRandomEmoji.setOnClickListener {
            viewModel.getRandomEmojiFromList()
        }

        btnEmojiList.setOnClickListener {
            activityNavigator.openEmojiActivity(this)
        }

        btnSearchAvatar.setOnClickListener {
            avatarViewModel.getAvatarUser(etSearchAvatar.text.toString())
        }

        btnAvatarList.setOnClickListener {
            activityNavigator.openAvatarActivity(this)
        }
    }

    override fun onStart() {
        super.onStart()
        setupView()
        setupObservable()
    }

}