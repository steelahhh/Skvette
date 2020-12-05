/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui

import android.content.Context
import android.graphics.Color
import dev.steelahhh.core.ColorRef

sealed class Luminance {
  object Light : Luminance()
  object NearlyWhite : Luminance()
  object Dark : Luminance()
}

val Int.luminance: Luminance
  get() {
    val red = 0.299 * Color.red(this)
    val green = 0.587 * Color.green(this)
    val blue = 0.114 * Color.blue(this)

    val darkness = 1 - (red + green + blue) / 255

    return when {
      darkness < 0.04 -> Luminance.NearlyWhite
      darkness >= 0.4 -> Luminance.Dark
      else -> Luminance.Light
    }
  }

fun Int.isColorDark(): Boolean = luminance is Luminance.Dark
fun ColorRef.isColorDark(context: Context): Boolean = create(context).luminance is Luminance.Dark
