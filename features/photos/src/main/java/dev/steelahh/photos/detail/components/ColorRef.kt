/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.graphics.Color
import dev.steelahhh.core.ColorRef

@Composable
fun ColorRef.asColor(): Color {
    val context = ContextAmbient.current
    return Color(create(context))
}
