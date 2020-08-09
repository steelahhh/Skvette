/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import dev.steelahhh.core.statusbar.StatusBarController

@Composable
fun ToolbarIconButton(
    icon: VectorAsset,
    modifier: Modifier = Modifier.padding(start = 8.dp, end = 8.dp),
    action: () -> Unit
) {
    Box(
        shape = CircleShape,
        modifier = modifier.then(
            Modifier
                .padding(top = StatusBarController.heightDp.dp + 8.dp)
                .size(48.dp)
                .clip(CircleShape)
        )
    ) {
        Icon(
            asset = icon.copy(defaultHeight = 32.dp, defaultWidth = 32.dp),
            tint = Color.White,
            modifier = Modifier.clickable(onClick = action).padding(8.dp)
        )
    }
}
