/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.statusbar

import android.graphics.Rect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("ObjectPropertyName")
object WindowInsetsHolder {
  private val _rect = MutableStateFlow(Rect())

  fun get(): Rect = _rect.value

  fun flow(): Flow<Rect> = _rect

  fun newInsets(insets: Rect) {
    _rect.value = insets
  }
}
