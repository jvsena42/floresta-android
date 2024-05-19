package com.florestaandroid.config.di

import android.app.Application
import com.florestaandroid.data.datasource.NodeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideNodeClient(
        @ApplicationContext appContext: Application,
    ): NodeClient {
        return NodeClient()
    }


}