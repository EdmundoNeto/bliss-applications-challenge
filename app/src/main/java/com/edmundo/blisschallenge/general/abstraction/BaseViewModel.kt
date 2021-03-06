package com.edmundo.blisschallenge.general.abstraction

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edmundo.blisschallenge.general.utils.State

abstract class BaseViewModel : ViewModel() {
    //Control loader view visibility
    val mainLoaderVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    //Control content view visibility
    val contendVisibility = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }

    //Control error view visibility
    val errorVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun getVisibility(show: Boolean): Int = takeIf { show }?.run {
        View.VISIBLE
    } ?: View.GONE

    fun setState(state: State) {
        contendVisibility.postValue(
            when (state) {
                State.SUCCESS -> getVisibility(true)
                State.ERROR, State.LOADING, State.REFRESH_LOADING -> getVisibility(
                    false
                )
            }
        )

        mainLoaderVisibility.postValue(
            when (state) {
                State.LOADING -> getVisibility(true)
                State.SUCCESS, State.ERROR, State.REFRESH_LOADING -> getVisibility(
                    false
                )
            }
        )

        errorVisibility.postValue(
            when (state) {
                State.ERROR -> getVisibility(true)
                State.SUCCESS, State.LOADING, State.REFRESH_LOADING -> getVisibility(
                    false
                )
            }
        )
    }
}