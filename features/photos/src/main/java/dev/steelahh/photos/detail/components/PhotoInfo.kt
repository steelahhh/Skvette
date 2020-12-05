/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import dev.steelahh.photos.R
import dev.steelahhh.coreui.compose.ColumnItem
import dev.steelahhh.data.models.ExifUi
import dev.steelahhh.data.models.PhotoUi

@Composable
internal fun PhotoInfoRow(exifUi: ExifUi) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Row {
            ColumnItem(
                title = stringResource(id = R.string.camera_title),
                value = exifUi.camera,
                modifier = Modifier.weight(1f)
            )
            ColumnItem(
                title = stringResource(id = R.string.aperture_title),
                value = exifUi.aperture,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            ColumnItem(
                title = stringResource(id = R.string.focal_length_title),
                value = exifUi.focalLength,
                modifier = Modifier.weight(1f)
            )
            ColumnItem(
                title = stringResource(id = R.string.shutter_speed_title),
                value = exifUi.shutterSpeed,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            ColumnItem(
                title = stringResource(id = R.string.iso_title),
                value = exifUi.ISO,
                modifier = Modifier.weight(1f)
            )
            ColumnItem(
                title = stringResource(id = R.string.dimensions_title),
                value = exifUi.dimensions,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
internal fun CommonPhotoInfoRow(photo: PhotoUi) {
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
        photo.info.forEach { item ->
            ColumnItem(
                title = stringResource(id = item.key).toUpperCase(Locale.current),
                value = item.value,
                modifier = Modifier.weight(1f),
                horizontalGravity = Alignment.CenterHorizontally
            )
        }
    }
}
