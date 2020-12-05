/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import android.view.ViewGroup
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.google.android.material.composethemeadapter.MdcTheme
import dev.steelahh.photos.detail.components.ActionsRow
import dev.steelahh.photos.detail.components.CommonPhotoInfoRow
import dev.steelahh.photos.detail.components.PhotoHeader
import dev.steelahh.photos.detail.components.PhotoInfoRow
import dev.steelahh.photos.detail.components.ProfileRow
import dev.steelahh.photos.detail.components.TagsRow
import dev.steelahh.photos.detail.components.asColor
import dev.steelahhh.core.ColorRef
import dev.steelahhh.coreui.compose.ErrorComponent
import dev.steelahhh.coreui.compose.Loader
import dev.steelahhh.data.models.PhotoUi
import kotlinx.coroutines.flow.Flow

internal val CORNER_SIZE = 56.dp
internal val CONTENT_SHAPE = RoundedCornerShape(topRight = CORNER_SIZE)
internal val VISIBLE_IMAGE_HEIGHT = 420.dp
internal val ACTUAL_IMAGE_HEIGHT = VISIBLE_IMAGE_HEIGHT + CORNER_SIZE

@ExperimentalLayout
fun photoDetailUi(
    viewGroup: ViewGroup,
    stateFlow: Flow<PhotoDetailState>,
    actioner: (PhotoDetailAction) -> Unit,
    photoUrl: String,
    photoColor: ColorRef
) = viewGroup.setContent(Recomposer.current()) {
    MdcTheme(setTextColors = true) {
        val state by stateFlow.collectAsState(PhotoDetailState(photoUrl))
        ScrollableColumn {
            Stack {
                PhotoHeader(
                    placeholder = photoUrl,
                    photo = state.photo,
                    color = (state.photo?.colorRef ?: photoColor).asColor(),
                    actioner = actioner,
                )
                when {
                    state.isLoading -> LoadingContent(
                        modifier = Modifier
                            .gravity(Alignment.Center)
                            .padding(top = VISIBLE_IMAGE_HEIGHT)
                            .fillMaxWidth()
                    )
                    state.photo != null -> PhotoContent(
                        photo = state.photo!!,
                        modifier = Modifier.padding(top = VISIBLE_IMAGE_HEIGHT),
                        actioner = actioner
                    )
                    state.error != null -> ErrorComponent(
                        message = state.error!!,
                        onAction = { actioner(PhotoDetailAction.Refresh) }
                    )
                    else -> Text("Well hello there")
                }
            }

        }

    }
}

@Composable
private fun LoadingContent(modifier: Modifier) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = CONTENT_SHAPE,
        modifier = modifier,
    ) {
        Loader(modifier = Modifier.padding(56.dp))
    }
}

@ExperimentalLayout
@Composable
fun PhotoContent(
    photo: PhotoUi,
    modifier: Modifier,
    actioner: (PhotoDetailAction) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = CONTENT_SHAPE,
        modifier = modifier,
    ) {
        Column {
            ProfileRow(actioner, photo)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            CommonPhotoInfoRow(photo)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            ActionsRow(photo, actioner)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            photo.exif?.let { PhotoInfoRow(it) }
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            TagsRow(photo, actioner)
        }
    }
}
