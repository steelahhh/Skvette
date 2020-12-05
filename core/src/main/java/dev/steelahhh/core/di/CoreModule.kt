/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.di

import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import dagger.Module
import dagger.Provides
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.ProcessLifetime
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
object CoreModule {
  @Provides
  @ProcessLifetime
  fun provideLongLifetimeScope(): CoroutineScope = ProcessLifecycleOwner.get().lifecycle.coroutineScope

  @Singleton
  @Provides
  fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
    io = Dispatchers.IO,
    computation = Dispatchers.Default,
    main = Dispatchers.Main
  )
}
