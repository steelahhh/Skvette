/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.steelahhh.core.di.AppComponent
import dev.steelahhh.data.di.DataModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [DataModule::class]
)
interface ApplicationComponent : AppComponent {
  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): ApplicationComponent
  }
}
