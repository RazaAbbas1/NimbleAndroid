package com.android.sampleTest.di

import android.app.Application
import android.content.Context
import com.android.sampleTest.feature.repositories.LoginRepository
import com.android.sampleTest.feature.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun provideLoginRepository(repo: LoginRepository): MainRepository {
        return repo
    }
}