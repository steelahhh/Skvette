/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dev.steelahhh.skvette.data.photos.PhotosRepository
import io.github.steelahhh.core.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object DataModule {

    private const val TIME_OUT = 3000L
    private const val BASE_URL = "https://api.unsplash.com/"

    @JvmStatic
    @Provides
    @Reusable
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
            .addInterceptor(ApiKeyInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): SKVService = retrofit.create(SKVService::class.java)

    @JvmStatic
    @Provides
    @Reusable
    fun provideRepository(
        service: SKVService,
        schedulers: SchedulerProvider
    ) = PhotosRepository(service, schedulers)
}
