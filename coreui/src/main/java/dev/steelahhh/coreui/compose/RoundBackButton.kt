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
import androidx.ui.core.clip
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.padding
import androidx.ui.layout.size
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.rounded.ArrowBack
import androidx.ui.unit.dp
import dev.steelahhh.core.statusbar.StatusBarController

@Composable
fun RoundBackButton(
    modifier: Modifier = Modifier
        .padding(
            start = 8.dp,
            top = StatusBarController.heightDp.dp + 8.dp,
            end = 8.dp
        )
        .size(48.dp),
    action: () -> Unit
) {
    Box(
        shape = CircleShape,
        backgroundColor = Color.White.copy(alpha = 0.7f),
        modifier = modifier + Modifier.clip(CircleShape)
    ) {
        Icon(
            asset = Icons.Rounded.ArrowBack.copy(defaultHeight = 32.dp, defaultWidth = 32.dp),
            modifier = Modifier.clickable(onClick = action).padding(8.dp)
        )
    }
}
