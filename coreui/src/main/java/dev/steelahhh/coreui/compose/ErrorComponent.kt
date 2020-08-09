/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorComponent(
    title: String = "Error",
    modifier: Modifier = Modifier,
    message: String? = null,
    onAction: () -> Unit,
) {
    Column(modifier = modifier.then(Modifier.fillMaxSize())) {
        Text(text = "To be implemented...")
    }
}
