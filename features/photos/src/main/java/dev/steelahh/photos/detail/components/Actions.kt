/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.steelahh.photos.detail.PhotoDetailAction
import dev.steelahh.photos.detail.PhotoUserAction
import dev.steelahhh.data.models.PhotoUi

@Composable
internal fun ActionsRow(photo: PhotoUi, actioner: (PhotoDetailAction) -> Unit) {
    ScrollableRow(contentPadding = InnerPadding(16.dp)) {
        PhotoUserAction.values().forEachIndexed { index, photoUserAction ->
            val (title, icon) = stringResource(id = photoUserAction.titleRes) to photoUserAction.icon
            Card(
                modifier = Modifier.preferredWidth(108.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    horizontalGravity = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable(onClick = { actioner(photoUserAction.toPhotoDetailAction(photo)) })
                        .padding(horizontal = 8.dp, vertical = 12.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        asset = icon.copy(
                            defaultHeight = icon.defaultHeight * 1.3f,
                            defaultWidth = icon.defaultHeight * 1.3f,
                        ),
                        tint = if (photoUserAction == PhotoUserAction.LIKE && photo.isLikedByUser)
                            Color.Red
                        else
                            contentColor()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = title.toUpperCase(Locale.current),
                        style = MaterialTheme.typography.overline.copy(fontSize = 12.sp)
                    )
                }
            }
            if (index != PhotoUserAction.values().lastIndex) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

private fun PhotoUserAction.toPhotoDetailAction(photo: PhotoUi) = when (this) {
    PhotoUserAction.LIKE -> PhotoDetailAction.Like(photo = photo)
    PhotoUserAction.SET_AS_WALLPAPER -> PhotoDetailAction.SetAsWallpaper(photo = photo)
    PhotoUserAction.DOWNLOAD -> PhotoDetailAction.Download(photo = photo)
    PhotoUserAction.COLLECTION -> PhotoDetailAction.AddToCollection(photo = photo)
}

