package com.ahuaman.client.data.google.di

import android.content.Context
import com.ahuaman.client.data.google.repo.GoogleRepository
import com.ahuaman.client.data.google.repo.IGoogleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleDependencies {

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(@ApplicationContext context: Context): GoogleAuthUiClient {
        return GoogleAuthUiClient(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class GoogleDependenciesModule {

    @Binds
    @Singleton
    abstract fun bindGoogleRepository(googleRepository: GoogleRepository): IGoogleRepository
}