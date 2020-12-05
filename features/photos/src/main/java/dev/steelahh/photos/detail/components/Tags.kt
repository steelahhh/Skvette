/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.material.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
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
