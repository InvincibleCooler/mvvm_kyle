package kyle.mvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import kyle.mvvm.net.RequestClient
import kyle.mvvm.net.RetrofitClient
import kyle.mvvm.net.ServiceApi
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger
import okhttp3.OkHttpClient

/**
 * Copyright (C) 2022 Kakao corp. All rights reserved.
 *
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TAG = "NetworkModule"

    private val log = Logger(TAG).apply {
        useThreadInfo = true
        category = Category.Module
    }

    @DefaultOkHttpClient
    @Provides
    fun provideDefaultOkHttpClient(): OkHttpClient {
        log.info("provideDefaultOkHttpClient()")
        return RequestClient().client
    }

    @Provides
    fun provideServiceApi(
        @DefaultOkHttpClient okHttpClient: OkHttpClient
    ): ServiceApi {
        log.info("provideServiceApi()")
        return RetrofitClient.getInstance(okHttpClient)
            .create(ServiceApi::class.java)
    }
}