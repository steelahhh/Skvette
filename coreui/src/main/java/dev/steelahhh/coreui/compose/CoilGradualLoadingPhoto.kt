/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxWidth
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.coil.SuccessResult

@Composable
fun CoilGradualLoadingPhoto(
    placeholder: String,
    actualPhoto: String?,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Stack {
        var isPhotoLoaded by state { false }

        CoilImage(
            data = placeholder,
            contentScale = contentScale,
            modifier = modifier.drawOpacity(if (isPhotoLoaded) 0f else 1f)
        )

        actualPhoto?.let {
            CoilImage(
                data = actualPhoto,
                contentScale = contentScale,
                modifier = modifier.drawOpacity(if (isPhotoLoaded) 1f else 0f)
            ) {
                isPhotoLoaded = it is SuccessResult
            }
        }
    }
}
