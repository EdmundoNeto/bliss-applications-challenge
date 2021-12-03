package com.edmundo.blisschallenge.github.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edmundo.blisschallenge.domain.mapper.AvatarMapperApiDataToRoom
import com.edmundo.blisschallenge.general.abstraction.*
import com.edmundo.blisschallenge.general.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubAvatarViewModel @Inject constructor(
    private val repository: IGithubRepository,
    private val iMapperRoomToApiData: IMapperRoomToApiData<IAvatarEntity, IAvatarResponse>,
    private val iMapperApiDataToRoom: IMapperApiDataToRoom<IAvatarResponse, IAvatarEntity>
) : BaseViewModel() {

    private val _githubUserAvatar: MutableLiveData<String> = MutableLiveData()
    val githubUserAvatar: LiveData<String> = _githubUserAvatar

    private val _githubAvatarList: MutableLiveData<List<IAvatarResponse>> = MutableLiveData()
    val githubAvatarList: LiveData<List<IAvatarResponse>> = _githubAvatarList

    private val _githubAvatarItemRemoved: MutableLiveData<Boolean> = MutableLiveData()
    val githubAvatarItemRemoved: LiveData<Boolean> = _githubAvatarItemRemoved

    val noGithubAvatarListFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    private fun validateData() {
        if (githubAvatarList.value == null)
            noGithubAvatarListFoundVisibility.value = getVisibility(true)
    }

    private fun clearEmptyWarning() {
        noGithubAvatarListFoundVisibility.value = getVisibility(false)
    }

    fun getAvatarList() {
        clearEmptyWarning()

        setState(State.LOADING)

        viewModelScope.launch(Dispatchers.IO)  {
            try {
                val avatarsFromDb = repository.getAllAvatarFromDb()

                if (avatarsFromDb.isEmpty()) {
                    setState(State.ERROR)
                } else {
                    _githubAvatarList.postValue(avatarsFromDb.map {
                        iMapperRoomToApiData.toApiData(it)
                    })
                    setState(State.SUCCESS)
                }

            } catch (ex: Exception) {
                Log.d("avatar: ", "${ex.message}")
                setState(State.ERROR)
            }
        }
    }

    fun removeAvatarFromList(iAvatarResponse: IAvatarResponse) {
        viewModelScope.launch(Dispatchers.IO)  {
            try {
                repository.deleteAvatarFromDb(iMapperApiDataToRoom.toRoom(iAvatarResponse))
                _githubAvatarItemRemoved.postValue(true)
            } catch (ex: Exception) {
                Log.d("avatar: ", "${ex.message}")
            }
        }
    }

    fun getAvatarUser(login: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allAvatarFromDb = repository.getAllAvatarFromDb()

                if (allAvatarFromDb.isEmpty()) {
                    getAvatarUserFromApi(login)
                } else {
                    val avatartUserFromDb =
                        repository.getAvatarUserFromDb(login)?.let {
                            iMapperRoomToApiData.toApiData(
                                it
                            )
                        }

                    avatartUserFromDb?.let {
                        _githubUserAvatar.postValue(it.avatarUrl)
                    } ?: run {
                        getAvatarUserFromApi(login)
                    }
                }

            } catch (ex: Exception) {
                Log.d("avatar: ", "${ex.message}")
            }
        }
    }

    private fun getAvatarUserFromApi(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val avatarResponse = repository.getAvatar(login)

                if (avatarResponse.avatarUrl?.isNotEmpty() == true) {
                    repository.saveAvatarFromApiToDb(iMapperApiDataToRoom.toRoom(avatarResponse))
                    _githubUserAvatar.postValue(avatarResponse.avatarUrl)

                }

            } catch (ex: Exception) {
                Log.d("avatar: ", "${ex.message}")
            }
        }
    }
}