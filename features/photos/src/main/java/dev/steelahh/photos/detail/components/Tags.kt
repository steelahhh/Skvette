/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.ExperimentalLayout
import androidx.ui.layout.FlowRow
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import androidx.ui.util.fastForEach
import dev.steelahh.photos.detail.PhotoDetailAction
import dev.steelahhh.data.models.PhotoUi

@ExperimentalLayout
@Composable
internal fun TagsRow(
    photo: PhotoUi,
    actioner: (PhotoDetailAction) -> Unit
) {
    Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
        FlowRow(mainAxisSpacing = 4.dp, crossAxisSpacing = 6.dp) {
            photo.tags.fastForEach { title ->
                Card(
                    shape = CircleShape,
                    elevation = 4.dp,
                    modifier = Modifier,
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body1,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable(onClick = { actioner(PhotoDetailAction.OpenTag(title)) })
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }

            }
        }
    }
}
