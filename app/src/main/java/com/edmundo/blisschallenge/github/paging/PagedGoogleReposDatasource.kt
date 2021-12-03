package com.edmundo.blisschallenge.github.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import com.edmundo.blisschallenge.general.abstraction.IReposResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class PagedGoogleReposDatasource(private val repository: IGithubRepository,
                                 private val coroutineContext: CoroutineContext
) :
    PageKeyedDataSource<Int, IReposResponse>() {
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, IReposResponse>) {
        scope.launch {
            try {
                val response = repository.getGoogleRepositories("google",1, 10)
                callback.onResult(response, null, 2)

            }catch (exception : Exception){
                Log.e("PagedGoogleRepos: ", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, IReposResponse>) {
        scope.launch {
            try {
                val response = repository.getGoogleRepositories("google",params.key, 10)
                callback.onResult(response, params.key+1)

            }catch (exception : Exception){
                Log.e("PagedGoogleRepos: ", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, IReposResponse>) {
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }


}