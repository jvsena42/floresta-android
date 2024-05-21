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
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideNodeClient(dispatcher: CoroutineDispatcher): NodeClient {
        return NodeClient(dispatcher)
    }

    @Provides
    @ApplicationContext
    fun provideApplication(application: Application): Application {
        return application
    }
    @Provides
    fun provideDaemon(
        @ApplicationContext application: Application,
    ): FlorestaDaemon {
        return FlorestaDaemonImpl(application)
    }

}