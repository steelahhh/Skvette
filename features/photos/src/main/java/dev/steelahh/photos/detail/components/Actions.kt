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
import androidx.ui.foundation.Icon
import androidx.ui.foundation.ScrollableRow
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.contentColor
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.intl.Locale
import androidx.ui.layout.Column
import androidx.ui.layout.InnerPadding
import androidx.ui.layout.Spacer
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.layout.preferredWidth
import androidx.ui.layout.size
import androidx.ui.layout.width
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.res.stringResource
import androidx.ui.text.toUpperCase
import androidx.ui.unit.dp
import androidx.ui.unit.sp
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

