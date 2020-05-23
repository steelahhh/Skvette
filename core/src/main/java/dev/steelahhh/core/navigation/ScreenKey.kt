/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

abstract class ScreenKey : Parcelable {
    val fragmentTag: String get() = toString()

    fun newFragment(): Fragment = createFragment().apply {
        arguments = (arguments ?: Bundle()).also {
            it.putParcelable("KEY", this@ScreenKey)
        }
    }

    protected abstract fun createFragment(): Fragment
}
