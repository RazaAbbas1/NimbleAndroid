package com.android.sampleTest.di

import android.app.Application
import android.content.Context
import com.android.sampleTest.repositories.LoginRepository
import com.android.sampleTest.repositories.MainRepository
import com.android.sampleTest.repositories.SurveyDataRepository
import com.android.sampleTest.repositories.SurveyRepository
import com.android.sampleTest.session.SessionManager
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
    fun provideSessionManager(mContext: Context): SessionManager {
        return SessionManager(mContext)
    }
    @Provides
    @Singleton
    fun provideLoginRepository(repo: LoginRepository): MainRepository {
        return repo
    }

    @Provides
    @Singleton
    fun provideSurveyRepository(repo: SurveyRepository): SurveyDataRepository {
        return repo
    }
}