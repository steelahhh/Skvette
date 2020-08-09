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
import androidx.ui.foundation.drawBackground
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import dev.steelahh.photos.detail.ACTUAL_IMAGE_HEIGHT
import dev.steelahh.photos.detail.PhotoDetailAction
import dev.steelahhh.coreui.compose.CoilGradualLoadingPhoto
import dev.steelahhh.coreui.compose.RoundBackButton

@Composable
internal fun PhotoHeader(
    placeholder: String,
    photo: String?,
    color: Color = Color.Gray,
    actioner: (PhotoDetailAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().height(ACTUAL_IMAGE_HEIGHT).drawBackground(color)) {
        Stack {
            Column(modifier = Modifier.fillMaxWidth()) {
                CoilGradualLoadingPhoto(
                    placeholder = placeholder,
                    actualPhoto = photo,
                    modifier = Modifier.fillMaxWidth().height(ACTUAL_IMAGE_HEIGHT)
                )
            }

            RoundBackButton {
                actioner(PhotoDetailAction.GoBack)
            }
        }
    }
}
