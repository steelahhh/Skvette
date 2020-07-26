/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.views.navigation

import android.view.View
import dev.steelahhh.core.ColorRef
import dev.steelahhh.core.DrawableRef
import dev.steelahhh.coreui.extensions.dp

sealed class FloatingNavigationItem(val isActive: Boolean) {
    data class Icon(
        val _isActive: Boolean,
        val icon: DrawableRef,
        val iconTint: ColorRef,
        val selectionColor: ColorRef
    ) : FloatingNavigationItem(isActive = _isActive)

    data class Custom(
        val _isActive: Boolean,
        val view: View
    ) : FloatingNavigationItem(isActive = _isActive) {
        companion object {
            fun create(isActive: Boolean, factory: (size: Int) -> View) = Custom(
                _isActive = isActive,
                view = factory(48.dp)
            )
        }
    }
}
