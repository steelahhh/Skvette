/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.layout.size
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.unit.dp

@Composable
fun Loader(
    modifier: Modifier
) {
    CircularProgressIndicator(modifier = modifier.plus(Modifier.size(56.dp)))
}
