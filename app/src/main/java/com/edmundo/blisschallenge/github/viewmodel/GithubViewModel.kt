package com.edmundo.blisschallenge.github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edmundo.blisschallenge.general.abstraction.IEmoji
import com.edmundo.blisschallenge.general.abstraction.IEmojiEntity
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val repository: IGithubRepository
) : ViewModel() {

    private val _emojiList = MutableLiveData<List<IEmojiEntity>>()
    val emojiList: LiveData<List<IEmojiEntity>> = _emojiList

    fun getEmojiList() {
        viewModelScope.launch {
            val emojisDb = getEmojisFromDb()

            if (emojisDb.isEmpty()) {
                repository.getEmojis().collect {
                    _emojiList.value = it
                }
            } else
                _emojiList.value = emojisDb
        }
    }

    private fun getEmojisFromDb(): List<IEmojiEntity> {
        val list = mutableListOf<IEmojiEntity>()
        viewModelScope.launch {
            val emojisFromDb = repository.getEmojisFromDb()
            emojisFromDb.collect {
                list.addAll(it)
            }
        }
        return list

    }

}