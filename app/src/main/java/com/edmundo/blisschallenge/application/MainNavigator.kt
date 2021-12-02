package com.edmundo.blisschallenge.application

import android.content.Context
import android.content.Intent
import com.edmundo.blisschallenge.general.abstraction.ActivityNavigator
import com.edmundo.blisschallenge.github.activity.GithubAvatarsActivity
import com.edmundo.blisschallenge.github.activity.GithubEmojisActivity

class MainNavigator: ActivityNavigator {

    override fun openEmojiActivity(context: Context) {
        context.startActivity(Intent(context, GithubEmojisActivity::class.java))
    }

    override fun openAvatarActivity(context: Context) {
        context.startActivity(Intent(context, GithubAvatarsActivity::class.java))
    }
}