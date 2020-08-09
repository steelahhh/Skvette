/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.currentTextStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview

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
