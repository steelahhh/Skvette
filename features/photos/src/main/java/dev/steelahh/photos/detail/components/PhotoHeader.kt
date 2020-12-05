/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.steelahh.photos.detail.ACTUAL_IMAGE_HEIGHT
import dev.steelahh.photos.detail.PhotoDetailAction
import dev.steelahhh.coreui.compose.CoilGradualLoadingPhoto
import dev.steelahhh.coreui.compose.ToolbarIconButton
import dev.steelahhh.data.models.PhotoUi

@Composable
internal fun PhotoHeader(
  placeholder: String,
  photo: PhotoUi?,
  color: Color = Color.Gray,
  actioner: (PhotoDetailAction) -> Unit
) {
  Column(modifier = Modifier.fillMaxSize().height(ACTUAL_IMAGE_HEIGHT).background(color = color)) {
    Stack {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .drawOverlay(Color.Black.copy(alpha = 0.2f))
      ) {
        CoilGradualLoadingPhoto(
          placeholder = placeholder,
          actualPhoto = photo?.url,
          modifier = Modifier.fillMaxWidth().height(ACTUAL_IMAGE_HEIGHT)
        )
      }

      ToolbarIconButton(Icons.Rounded.ArrowBack) {
        actioner(PhotoDetailAction.GoBack)
      }

      photo?.unsplashUrl?.let {
        ToolbarIconButton(
          icon = Icons.Rounded.Public,
          modifier = Modifier.padding(start = 8.dp, end = 8.dp).gravity(Alignment.TopEnd)
        ) {
          actioner(PhotoDetailAction.OpenInBrowser(it))
        }
      }
    }
  }
}
