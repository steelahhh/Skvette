/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.steelahhh.core.SchedulerProvider
import io.github.steelahhh.core.SchedulerProviderImpl

@Module
object CoreModule {
    @Provides
    @JvmStatic
    @Reusable
    fun provideSchedulers(): SchedulerProvider = SchedulerProviderImpl()
}
