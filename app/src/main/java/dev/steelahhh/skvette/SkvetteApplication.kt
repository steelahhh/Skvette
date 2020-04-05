/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxViewModelConfigFactory
import dev.steelahhh.skvette.di.ApplicationComponent
import dev.steelahhh.skvette.di.DaggerApplicationComponent
import dev.steelahhh.skvette.di.InjectorProvider
import timber.log.Timber

@Suppress("unused")
class SkvetteApplication : Application(), InjectorProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        setupTheme()
        setupTimber()
        setupMvRx()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupMvRx() {
        MvRx.viewModelConfigFactory = MvRxViewModelConfigFactory(this)
    }

    private fun setupTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_UNSPECIFIED)
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
