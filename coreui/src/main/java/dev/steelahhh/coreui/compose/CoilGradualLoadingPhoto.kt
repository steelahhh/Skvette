/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.compose

import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState

@Composable
fun CoilGradualLoadingPhoto(
  placeholder: String,
  actualPhoto: String?,
  contentScale: ContentScale = ContentScale.Crop,
  modifier: Modifier = Modifier.fillMaxWidth()
) {
  Stack {
    var isPhotoLoaded by remember { mutableStateOf(false) }

    CoilImage(
      data = placeholder,
      contentScale = contentScale,
      modifier = modifier.drawOpacity(if (isPhotoLoaded) 0f else 1f)
    )

    actualPhoto?.let {
      CoilImage(
        data = actualPhoto,
        contentScale = contentScale,
        modifier = modifier.drawOpacity(if (isPhotoLoaded) 1f else 0f),
        onRequestCompleted = {
          isPhotoLoaded = it is ImageLoadState.Success
        }
      )
    }
  }
}
