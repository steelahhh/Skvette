/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredWidth
import androidx.ui.layout.size
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.steelahh.photos.R
import dev.steelahh.photos.detail.PhotoDetailAction
import dev.steelahhh.coreui.compose.pluralStringResource
import dev.steelahhh.data.models.PhotoUi
import dev.steelahhh.data.models.UserPreviewUi

@Composable
internal fun ProfileRow(
    actioner: (PhotoDetailAction) -> Unit,
    photo: PhotoUi
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { actioner(PhotoDetailAction.OpenUser(photo.author.id)) })
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalGravity = Alignment.CenterVertically
    ) {
        Surface(
            color = Color.Gray,
            elevation = 8.dp,
            shape = CircleShape,
            modifier = Modifier.size(48.dp),
        ) {
            photo.author.profileImage.medium?.let {
                CoilImage(data = it, modifier = Modifier.size(48.dp))
            }
        }
        Spacer(modifier = Modifier.preferredWidth(12.dp))
        Column {
            Text(
                text = photo.author.name,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = makeProfileSubtitle(author = photo.author),
                style = MaterialTheme.typography.caption.copy(color = Color.Gray)
            )
        }
    }
}

@Composable
private fun makeProfileSubtitle(author: UserPreviewUi): String {
    val photos = pluralStringResource(
        R.plurals.photos_pattern,
        author.totalPhotos,
        author.totalPhotos
    )
    val collections = pluralStringResource(
        R.plurals.collections_pattern,
        author.totalCollections,
        author.totalCollections
    ).takeIf { author.totalCollections > 0 }

    return listOfNotNull(photos, collections).joinToString()
}
