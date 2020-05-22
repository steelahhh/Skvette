/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.di

import android.app.Activity
import androidx.fragment.app.Fragment

interface InjectorProvider {
    val component: AppComponent
}

val Activity.injector get() = (application as InjectorProvider).component

val Fragment.injector get() = requireActivity().injector
