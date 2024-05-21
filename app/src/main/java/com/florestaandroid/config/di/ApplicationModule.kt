package com.florestaandroid.config.di

import android.app.Application
import com.florestaandroid.data.datasource.NodeClient
import com.florestaandroid.domain.daemon.FlorestaDaemon
import com.florestaandroid.domain.daemon.FlorestaDaemonImpl
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

    ): NodeClient {
        return NodeClient()
    }
    @Provides
    fun provideDaemon(
        @ApplicationContext application: Application,
    ): FlorestaDaemon {
        return FlorestaDaemonImpl(application.dataDir.toString())
    }

}