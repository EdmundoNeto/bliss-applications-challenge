package com.edmundo.blisschallenge.github.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.edmundo.blisschallenge.general.abstraction.BaseViewModel
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import com.edmundo.blisschallenge.general.abstraction.IReposResponse
import com.edmundo.blisschallenge.github.paging.PagedGoogleReposDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(private val repository: IGithubRepository) :
    BaseViewModel() {
    var reposLiveData: LiveData<PagedList<IReposResponse>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        reposLiveData = initializedPagedListBuilder(config).build()
    }

    fun getGithubRepos(): LiveData<PagedList<IReposResponse>> = reposLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, IReposResponse> {

        val dataSourceFactory = object : DataSource.Factory<Int, IReposResponse>() {
            override fun create(): DataSource<Int, IReposResponse> {
                return PagedGoogleReposDatasource(repository, Dispatchers.IO)
            }
        }
        return LivePagedListBuilder<Int, IReposResponse>(dataSourceFactory, config)
    }
}