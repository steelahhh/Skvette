/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.updatePadding
import com.google.android.material.appbar.MaterialToolbar
import dev.steelahhh.core.statusbar.StatusBarController
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
open class SkvetteToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialToolbar(context, attrs, defStyleAttr) {

    var padToStatus: Boolean = false
        set(value) {
            field = value
            if (value) {
                updatePadding(top = StatusBarController.height)
            }
        }
}
