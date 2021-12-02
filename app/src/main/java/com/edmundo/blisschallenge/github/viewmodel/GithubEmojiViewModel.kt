package com.edmundo.blisschallenge.github.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.edmundo.blisschallenge.domain.mapper.EmojiMapperApiDataToRoom
import com.edmundo.blisschallenge.general.abstraction.BaseViewModel
import com.edmundo.blisschallenge.general.abstraction.IEmoji
import com.edmundo.blisschallenge.general.abstraction.IEmojiEntity
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import com.edmundo.blisschallenge.general.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubEmojiViewModel @Inject constructor(
    private val repository: IGithubRepository
) : BaseViewModel() {

    private val _githubEmojiList: MutableLiveData<List<IEmoji>> = MutableLiveData()
    val githubEmojiList: LiveData<List<IEmoji>> = _githubEmojiList

    private val _randomEmoji: MutableLiveData<String> = MutableLiveData()
    val randomEmoji :LiveData<String> = _randomEmoji

    val noGithubEmojiListFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    private fun validateData() {
        if (_githubEmojiList.value == null)
            noGithubEmojiListFoundVisibility.value = getVisibility(true)
    }

    private fun clearEmptyWarning() {
        noGithubEmojiListFoundVisibility.value = getVisibility(false)
    }

    fun getRandomEmojiFromList() {
        _randomEmoji.value = _githubEmojiList.value?.random()?.url
    }

    fun getEmojiList() {
        clearEmptyWarning()

        setState(State.LOADING)

        viewModelScope.launch {
            try {
                val emojisFromDb = repository.getEmojisFromDb()

                if (emojisFromDb.isEmpty()) {
                    getEmojisFromApi()
                } else {
                    _githubEmojiList.value =
                        emojisFromDb.map { EmojiMapperApiDataToRoom.toApiData(it) }
                    setState(State.SUCCESS)
                }

                getRandomEmojiFromList()
            } catch (ex: Exception) {
                setState(State.ERROR)
            }
        }
    }

    private fun getEmojisFromApi() {
        viewModelScope.launch {
            try {
                val emojiResponse = repository.getEmojis()

                if (emojiResponse.emojiList.isEmpty())
                    setState(State.ERROR)
                else {
                    repository.saveEmojisFromApiToDb(emojiResponse.emojiList)
                    _githubEmojiList.value = emojiResponse.emojiList
                    validateData()

                    setState(State.SUCCESS)
                }

            } catch (ex: Exception) {
                setState(State.ERROR)
            }
        }
    }
}