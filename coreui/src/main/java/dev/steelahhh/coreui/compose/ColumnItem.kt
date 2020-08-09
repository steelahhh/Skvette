/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.currentTextStyle
import androidx.ui.intl.Locale
import androidx.ui.layout.Column
import androidx.ui.layout.RowScope.weight
import androidx.ui.layout.Spacer
import androidx.ui.layout.height
import androidx.ui.material.MaterialTheme
import androidx.ui.text.toUpperCase
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp

@Composable
fun ColumnItem(
    title: String,
    value: String,
    modifier: Modifier,
    horizontalGravity: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalGravity = horizontalGravity) {
        Text(
            text = title.toUpperCase(Locale.current),
            style = MaterialTheme.typography.overline.copy(
                fontSize = 12.sp,
                color = currentTextStyle().color.copy(0.7f)
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            fontSize = 14.sp
        )
    }
}

@Composable
@Preview
internal fun Preview() {
    ColumnItem(title = "Test", value = "Value", modifier = Modifier.weight(1f))
}
