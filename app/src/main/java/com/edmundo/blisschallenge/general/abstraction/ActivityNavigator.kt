package com.edmundo.blisschallenge.general.abstraction

import android.content.Context

interface ActivityNavigator {
    fun openEmojiActivity(context: Context)
    fun openAvatarActivity(context: Context)
    fun openReposActivity(context: Context)
}