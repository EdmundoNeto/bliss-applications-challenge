package com.edmundo.blisschallenge.domain.di

import com.edmundo.blisschallenge.domain.GithubService
import com.edmundo.blisschallenge.domain.database.dao.EmojiDao
import com.edmundo.blisschallenge.domain.repository.GithubRepository
import com.edmundo.blisschallenge.general.abstraction.IGithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(dao: EmojiDao, githubService: GithubService): IGithubRepository {
        return GithubRepository(dao, githubService)
    }

}
