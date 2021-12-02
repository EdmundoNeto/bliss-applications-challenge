package com.edmundo.blisschallenge.github.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.edmundo.blisschallenge.BR
import com.edmundo.blisschallenge.R
import com.edmundo.blisschallenge.databinding.ActivityMainBinding
import com.edmundo.blisschallenge.general.EmojiNavigator
import com.edmundo.blisschallenge.general.extensions.bindingContentView
import com.edmundo.blisschallenge.general.extensions.observe
import com.edmundo.blisschallenge.github.viewmodel.GithubEmojiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<GithubEmojiViewModel>()
    @Inject
    lateinit var emojiNavigator: EmojiNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingContentView(R.layout.activity_main).run {
            setVariable(BR.githubEmojiViewModel, viewModel)
            lifecycleOwner = this@MainActivity
        }


        setupView()
    }

    private fun setupView() {
        viewModel.getEmojiList()

        btnRandomEmoji.setOnClickListener {
            viewModel.getRandomEmojiFromList()
        }

        btnEmojiList.setOnClickListener {
            emojiNavigator.openEmojiActivity(this)
        }
    }

}