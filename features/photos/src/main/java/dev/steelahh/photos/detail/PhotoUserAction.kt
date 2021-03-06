/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.FilterFrames
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.VectorAsset
import dev.steelahh.photos.R

enum class PhotoUserAction(
  @StringRes
  val titleRes: Int,
  val icon: VectorAsset
) {
  LIKE(R.string.action_like, Icons.Default.ThumbUp),
  SHARE(R.string.action_share, Icons.Default.Share),
  SET_AS_WALLPAPER(R.string.action_wallpaper, Icons.Default.FilterFrames),
  DOWNLOAD(R.string.action_download, Icons.Default.CloudDownload),
  COLLECTION(R.string.action_collection, Icons.Default.Bookmark),
}
