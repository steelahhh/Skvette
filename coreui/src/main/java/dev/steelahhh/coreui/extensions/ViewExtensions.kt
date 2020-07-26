/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.updateLayoutParams
import dev.steelahhh.core.statusbar.StatusBarController

fun View.showSoftKeyboard() {
    if (requestFocus()) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideSoftKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.updateTopMarginToStatusBar(@Px additionalMargin: Int = 0) {
    updateLayoutParams<ViewGroup.LayoutParams> {
        when (this) {
            is ConstraintLayout.LayoutParams -> topMargin = StatusBarController.height + additionalMargin
            is FrameLayout.LayoutParams -> topMargin = StatusBarController.height + additionalMargin
        }
    }
}

fun Drawable.applyTint(@ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        DrawableCompat.setTint(this, color)
    } else {
        this.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}
