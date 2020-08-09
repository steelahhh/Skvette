/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.graphics.Color

fun Modifier.drawOverlay(
    color: Color
): Modifier {
    val overlay = drawWithContent {
        drawContent()
        drawRect(color)
    }
    return this.then(overlay)
}
