/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient

/**
 * Load a quantity string resource with formatting.
 *
 * @param id the resource identifier
 * @param count the actual count
 * @param formatArgs the format arguments
 * @return the string data associated with the resource
 */
@Composable
fun pluralStringResource(@PluralsRes id: Int, count: Int, vararg formatArgs: Any): String {
    val context = ContextAmbient.current
    return context.resources.getQuantityString(id, count, *formatArgs)
}
