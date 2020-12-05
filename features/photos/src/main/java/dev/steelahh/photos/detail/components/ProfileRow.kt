/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    verticalAlignment = Alignment.CenterVertically
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
