package com.edmundo.blisschallenge.application

import android.content.Context
import android.content.Intent
import com.edmundo.blisschallenge.general.EmojiNavigator
import com.edmundo.blisschallenge.github.activity.GithubEmojisActivity

class MainNavigator: EmojiNavigator {

    override fun openEmojiActivity(context: Context) {
        context.startActivity(Intent(context, GithubEmojisActivity::class.java))
    }
}